/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class CandidateDTO.
 * 
 * @author Luis Espinosa
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -896734731027508157L;

	/** The id. */
	private Long id;

	/** The alias. */
	private String alias;

	private String description;

	/** The image. */
	private String image;

}
