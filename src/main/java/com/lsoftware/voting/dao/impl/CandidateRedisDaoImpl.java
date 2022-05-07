/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.lsoftware.voting.dao.ICandidateRedisDao;
import com.lsoftware.voting.dto.CandidateDTO;
import com.lsoftware.voting.entity.Candidate;

/**
 * The Class CandidateRedisDaoImpl.
 * 
 * @author Luis Espinsosa
 */
@Repository
public class CandidateRedisDaoImpl implements ICandidateRedisDao {
	
	/** The Constant CANDIDATE_HASH_KEY. */
	public static final String CANDIDATE_HASH_KEY = "Candidate";
	
	/** The redis template. */
	private RedisTemplate redisTemplate;
	
	private ModelMapper modelMapper;
	
	/**
	 * Instantiates a new candidate redis dao impl.
	 *
	 * @param redisTemplate the redis template
	 */
	public CandidateRedisDaoImpl(RedisTemplate redisTemplate, ModelMapper modelMapper) {
		this.redisTemplate = redisTemplate;
		this.modelMapper = modelMapper;
	}
	

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Override
	public List<CandidateDTO> findAll() {
		List<Candidate> candidadtes = redisTemplate.opsForHash().values(CANDIDATE_HASH_KEY.concat("ListAll"));
		return candidadtes.stream()
				.map(c -> modelMapper.map(c, CandidateDTO.class))
				.collect(Collectors.toList());
	}
	
}
