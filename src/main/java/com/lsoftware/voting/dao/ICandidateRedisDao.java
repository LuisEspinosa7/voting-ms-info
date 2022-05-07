/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.dao;

import java.util.List;
import com.lsoftware.voting.dto.CandidateDTO;

/**
 * The Interface ICandidateRedisDao.
 * 
 * @author Luis Espinosa
 */
public interface ICandidateRedisDao {
	
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<CandidateDTO> findAll();

}
