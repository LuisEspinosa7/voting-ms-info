/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lsoftware.voting.dto.CandidateDTO;
import com.lsoftware.voting.repository.CandidateRepository;
import com.lsoftware.voting.shared.service.QueryBasicMethods;

/**
 * The Class CandidateService.
 * 
 * @author Luis Espinosa
 */
@Service
public class CandidateService implements QueryBasicMethods<CandidateDTO>{

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
	public Set<CandidateDTO> listAll() {
		LOG.info("method: listAll");
		
		return candidateRepository.findAll()
				.stream()
				.map(c -> modelMapper.map(c, CandidateDTO.class))
				.collect(Collectors.toSet());
	}

}
