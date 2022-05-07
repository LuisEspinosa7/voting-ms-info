/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lsoftware.voting.dto.CandidateCreationDTO;
import com.lsoftware.voting.dto.CandidateDTO;
import com.lsoftware.voting.dto.CandidateModifiableDTO;
import com.lsoftware.voting.service.CandidateService;
import com.lsoftware.voting.shared.api.ApiCustomResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * The Class CandidateController.
 * 
 * @author Luis Espinosa
 */
@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateController {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CandidateController.class);

	/** The candidate service. */
	private CandidateService candidateService;
	
	/**
	 * Instantiates a new candidate controller.
	 *
	 * @param candidateService the candidate service
	 */
	public CandidateController(CandidateService candidateService) {
		this.candidateService = candidateService;
	}
	
	
	/**
	 * List all.
	 *
	 * @return the response entity
	 */
	@Operation(summary = "List the available candidates")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "List the available candidates", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiCustomResponse.class)) }) })
	@GetMapping()
	public ResponseEntity<ApiCustomResponse> listAll() {
		LOG.info("method: listAll");

		List<CandidateDTO> results = candidateService.listAll();
		ApiCustomResponse response = new ApiCustomResponse.ApiResponseBuilder(200)
				.message("Candidates List").data(results).build();

		return ResponseEntity.ok(response);
	}
	
	
	/**
	 * Creates the.
	 *
	 * @param candidateCreationDTO the candidate creation DTO
	 * @return the response entity
	 */
	@Operation(summary = "Create a new Candidate")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Create the new candidate", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiCustomResponse.class)) }) })
	@PostMapping
	public ResponseEntity<ApiCustomResponse> create(@Valid @RequestBody CandidateCreationDTO candidateCreationDTO) {
		LOG.info("method: create");

		CandidateCreationDTO candidate = candidateService.add(candidateCreationDTO);
		ApiCustomResponse response = new ApiCustomResponse.ApiResponseBuilder(200).message("Candidate created")
				.data(candidate).build();

		return ResponseEntity.ok(response);
	}

	
	/**
	 * Update.
	 *
	 * @param candidateModifiableDTO the candidate modifiable DTO
	 * @return the response entity
	 */
	@Operation(summary = "Update a Candidate")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Update a candidate", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiCustomResponse.class)) }) })
	@PutMapping
	public ResponseEntity<ApiCustomResponse> update(@Valid @RequestBody CandidateModifiableDTO candidateModifiableDTO) {
		LOG.info("method: update");

		CandidateModifiableDTO category = candidateService.update(candidateModifiableDTO);
		ApiCustomResponse response = new ApiCustomResponse.ApiResponseBuilder(200).message("Candidate updated")
				.data(category).build();

		return ResponseEntity.ok(response);
	}

	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@Operation(summary = "Delete a Candidate")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Delete a candidate", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApiCustomResponse.class)) }) })
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiCustomResponse> delete(@PathVariable("id") @NotBlank @Size(min = 1, max = 10) Long id) {
		LOG.info("method: delete");

		candidateService.delete(id);
		ApiCustomResponse response = new ApiCustomResponse.ApiResponseBuilder(200).message("Candidate deleted").build();

		return ResponseEntity.ok(response);
	}
	
}
