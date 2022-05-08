/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class Vote.
 * 
 * @author Luis Espinosa
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "votes")
public class Vote {
	
	/** The id. */
	@Id
	@Column(name = "vot_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "can_id", updatable = false)
	private Candidate candidate;
	
	/** The code. */
	@Column(name = "vot_code", updatable = false)
	private String code;
	
	/** The timestamp. */
	@Column(name = "vot_timestamp", updatable = false)
	@CreationTimestamp
	private LocalDateTime timestamp;
	
	/** The document. */
	@Column(name = "voter_document")
	private String document;

}
