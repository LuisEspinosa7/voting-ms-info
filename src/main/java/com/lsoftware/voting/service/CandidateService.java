/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lsoftware.voting.dto.CandidateCreationDTO;
import com.lsoftware.voting.dto.CandidateDTO;
import com.lsoftware.voting.dto.CandidateModifiableDTO;
import com.lsoftware.voting.entity.Candidate;
import com.lsoftware.voting.exception.ExceptionInternalServerError;
import com.lsoftware.voting.exception.ExceptionValueNotPermitted;
import com.lsoftware.voting.repository.CandidateRepository;
import com.lsoftware.voting.shared.service.ServiceCreationMethods;
import com.lsoftware.voting.shared.service.ServiceListMethods;
import com.lsoftware.voting.shared.service.ServiceModifiableMethods;
import com.lsoftware.voting.shared.status.Status;

/**
 * The Class CandidateService.
 * 
 * @author Luis Espinosa
 */
@Service
public class CandidateService implements ServiceListMethods<CandidateDTO>, ServiceModifiableMethods<CandidateModifiableDTO>, ServiceCreationMethods<CandidateCreationDTO> {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CandidateService.class);
	
	/** The candidate repository. */
	private CandidateRepository candidateRepository;
	
	/** The model mapper. */
	private ModelMapper modelMapper;
	
	/**
	 * Instantiates a new candidate service.
	 *
	 * @param candidateRepository the candidate repository
	 * @param modelMapper the model mapper
	 */
	public CandidateService(CandidateRepository candidateRepository, ModelMapper modelMapper) {
		this.candidateRepository = candidateRepository;
		this.modelMapper = modelMapper;
	}
	
	/**
	 * List all.
	 *
	 * @return the sets the
	 */
	@Override
	@Cacheable(value = "CandidateListAll")
	public List<CandidateDTO> listAll() {
		LOG.info("method: listAll");
		
		return candidateRepository.findByStatus(Status.ACTIVE.getDigit())
				.stream()
				.map(c -> modelMapper.map(c, CandidateDTO.class))
				.collect(Collectors.toList());
	}

	/**
	 * Adds the.
	 *
	 * @param obj the obj
	 * @return the candidate creation DTO
	 */
	@Override
	@Transactional
	@CacheEvict(value = "CandidateListAll", allEntries=true)
	public CandidateCreationDTO add(CandidateCreationDTO obj) {
		LOG.info("method: add");
		
		Optional<Candidate> search = 
				candidateRepository.findByAliasAndStatus(obj.getAlias().toUpperCase(), 
						Status.ACTIVE.getDigit());
		
		if (search.isPresent()) throw new ExceptionValueNotPermitted("The candidate already exists");
		
		Candidate candidate = modelMapper.map(obj, Candidate.class);
		
		candidate.setAlias(candidate.getAlias().toUpperCase());
		candidate.setDescription(candidate.getDescription().toUpperCase());
		candidate.setStatus(Status.ACTIVE.getDigit());
		
		Candidate saved = candidateRepository.save(candidate);
		return modelMapper.map(saved, CandidateCreationDTO.class);
	}

	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the candidate modifiable DTO
	 */
	@Override
	@Transactional
	@CacheEvict(value = "CandidateListAll", allEntries=true)
	public CandidateModifiableDTO update(CandidateModifiableDTO obj) {
		LOG.info("method: update");
		
		Candidate candidate = candidateRepository
				.findByIdAndStatus(obj.getId(), Status.ACTIVE.getDigit())
				.orElseThrow(() -> new ExceptionValueNotPermitted("The candidate does not exists"));
		
		candidate.setImage(obj.getImage());
		candidate.setStatus(Status.ACTIVE.getDigit());
		
		Candidate saved = candidateRepository.save(candidate);
		return modelMapper.map(saved, CandidateModifiableDTO.class);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@Override
	@Transactional
	@CacheEvict(value = "CandidateListAll", allEntries=true)
	public void delete(Long id) {
		LOG.info("method: delete");
		
		Candidate candidate = candidateRepository
				.findByIdAndStatus(id, Status.ACTIVE.getDigit())
				.orElseThrow(() -> new ExceptionValueNotPermitted("The candidate does not exists"));
		
		int result = candidateRepository.setStatusById(Status.DELETED.getDigit(), candidate.getId());
		if (result < 1) throw new ExceptionInternalServerError("The candidate could not be deleted");
	}

}
