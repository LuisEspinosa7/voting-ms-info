/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.config;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * The Class KafkaConfig.
 * 
 * @author Luis Espinosa
 */
@Configuration
public class KafkaConfig {
	
	/** The bootstrap address. */
	@Value(value = "${spring.kafka.consumer.bootstrap-servers}")
	private String bootstrapAddress;
	
	/** The group id. */
	@Value(value = "${spring.kafka.consumer.group-id}")
	private String groupId;
	
	/**
	 * Consumer factory.
	 *
	 * @return the consumer factory
	 */
	@Bean
	public ConsumerFactory<String, Object> consumerFactory() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		properties.put(JsonDeserializer.TRUSTED_PACKAGES, "com.lsoftware.voting.model.kafka");
		
		return new DefaultKafkaConsumerFactory<>(properties);
	}

	/**
	 * Kafka listener container factory.
	 *
	 * @return the concurrent kafka listener container factory
	 */
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
