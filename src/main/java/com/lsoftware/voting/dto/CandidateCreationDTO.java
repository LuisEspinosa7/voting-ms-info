/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class CandidateCreationDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5950505020785602555L;

	/** The id. */
	private Long id;

	/** The alias. */
	@NotNull(message = "Alias can not be null")
	@NotEmpty(message = "Alias can not be empty")
	@Size(min=4, message="Alias should be composed of at least 4 characters")
	@Size(max=60, message="Alias could be composed of 60 characters maximum")
	private String alias;

	@NotNull(message = "Description can not be null")
	@NotEmpty(message = "Description can not be empty")
	@Size(min=20, message="Description should be composed of at least 20 characters")
	@Size(max=400, message="Alias could be composed of 400 characters maximum")
	private String description;

	/** The image. */
	@NotNull(message = "Image can not be null")
	@NotEmpty(message = "Image can not be empty")
	private String image;

}
