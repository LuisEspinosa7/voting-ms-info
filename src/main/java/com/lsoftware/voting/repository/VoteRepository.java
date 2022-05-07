/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lsoftware.voting.entity.Vote;

/**
 * The Interface VoteRepository.
 * 
 * @author Luis Espinosa
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{

}
