/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.model.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class VoteKafka.
 * 
 * @author Luis Espinosa
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteKafka {


	/** The document. */
	private String document;


	/** The code. */
	private String code;
	

	/** The candidate id. */
	private int candidateId;

}
