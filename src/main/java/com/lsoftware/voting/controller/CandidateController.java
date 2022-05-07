/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.controller;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lsoftware.voting.dto.CandidateDTO;
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

		Set<CandidateDTO> results = candidateService.listAll();
		ApiCustomResponse response = new ApiCustomResponse.ApiResponseBuilder(200)
				.message("Candidates List").data(results).build();

		return ResponseEntity.ok(response);
	}
	
}
