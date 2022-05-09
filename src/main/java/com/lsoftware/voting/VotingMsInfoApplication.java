/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * The Class VotingMsInfoApplication.
 * 
 * @author Luis Espinosa
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableAutoConfiguration
public class VotingMsInfoApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(VotingMsInfoApplication.class, args);
	}

}
