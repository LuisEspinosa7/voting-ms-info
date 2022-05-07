/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lsoftware.voting.entity.Candidate;

/**
 * The Interface CandidateRepository.
 * 
 * @author Luis Espinosa
 */
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	
	/**
	 * Find by status.
	 *
	 * @param status the status
	 * @return the list
	 */
	List<Candidate> findByStatus(Integer status);
	
	/**
	 * Find by alias and status.
	 *
	 * @param alias the alias
	 * @param status the status
	 * @return the optional
	 */
	Optional<Candidate> findByAliasAndStatus(String alias, Integer status);
	
	/**
	 * Find by id and status.
	 *
	 * @param id the id
	 * @param status the status
	 * @return the optional
	 */
	Optional<Candidate> findByIdAndStatus(Long id, Integer status);
	
	/**
	 * Sets the status by id.
	 *
	 * @param status the status
	 * @param id the id
	 * @return the int
	 */
	@Modifying
	@Query("update Candidate c SET c.status =:status WHERE c.id =:id")
	int setStatusById(@Param("status") Integer status, @Param("id") Long id);

}
