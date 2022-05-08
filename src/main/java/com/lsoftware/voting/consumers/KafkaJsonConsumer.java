/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.consumers;

import org.springframework.stereotype.Service;

import com.lsoftware.voting.entity.Candidate;
import com.lsoftware.voting.entity.Vote;
import com.lsoftware.voting.model.kafka.VoteKafka;
import com.lsoftware.voting.repository.CandidateRepository;
import com.lsoftware.voting.repository.VoteRepository;
import com.lsoftware.voting.shared.status.Status;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * The Class KafkaJsonConsumer.
 * 
 * @author Luis Espinosa
 */
@Service
public class KafkaJsonConsumer {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(KafkaJsonConsumer.class);
	
	/** The Constant VOTES_TOPIC. */
	private static final String VOTES_TOPIC = "votes";

	/** The vote repository. */
	private VoteRepository voteRepository;

	/** The model mapper. */
	private ModelMapper modelMapper;
	
	/** The candidate repository. */
	private CandidateRepository candidateRepository;

	/**
	 * Instantiates a new kafka json consumer.
	 *
	 * @param voteRepository the vote repository
	 */
	public KafkaJsonConsumer(VoteRepository voteRepository, ModelMapper modelMapper,
			CandidateRepository candidateRepository) {
		this.voteRepository = voteRepository;
		this.modelMapper = modelMapper;
		this.candidateRepository = candidateRepository;
	}

	/**
	 * Consume.
	 *
	 * @param message the message
	 */
	@KafkaListener(topics = VOTES_TOPIC, containerFactory = "kafkaListenerContainerFactory")
	public void consume(VoteKafka voteReceived) {
		LOG.info("Received: {}", voteReceived);
		Vote vote = modelMapper.map(voteReceived, Vote.class);
		
		Optional<Candidate> candidate = candidateRepository.findByIdAndStatus(Long.valueOf(String.valueOf(voteReceived.getCandidateId())), 
				Status.ACTIVE.getDigit());
		
		if (candidate.isPresent()) {
			vote.setId(0L);
			vote.setCandidate(candidate.get());
			voteRepository.save(vote);
		}
		
	}

}
