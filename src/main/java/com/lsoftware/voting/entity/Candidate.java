/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class Candidate.
 * 
 * @author Luis Espinosa
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candidates")
public class Candidate implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1710773117988263393L;

	/** The id. */
	@Id
	@Column(name = "can_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The alias. */
	@Column(name = "can_alias", unique = true)
	private String alias;
	
	@Column(name = "can_description")
	private String description;
	
	/** The status. */
	@Column(name = "can_status")
	private int status; 
	
	/** The image. */
	@Column(name = "can_image", unique = true)
	private String image;

}
