/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * The Class RedisConfig.
 * 
 * @author Luis Espinosa
 */
@Configuration
@EnableRedisRepositories
public class RedisConfig {
	
	/** The host. */
	@Value("${spring.redis.host}")
	private String host;

	/** The password. */
	@Value("${spring.redis.password}")
	private String password;

	/** The port. */
	@Value("${spring.redis.port}")
	private int port;

	/** The max total. */
	@Value("${spring.redis.jedis.pool.max-total}")
	private int maxTotal;

	/** The max idle. */
	@Value("${spring.redis.jedis.pool.max-idle}")
	private int maxIdle;

	/** The min idle. */
	@Value("${spring.redis.jedis.pool.min-idle}")
	private int minIdle;

	/**
	 * Gets the jedis client configuration.
	 *
	 * @return the jedis client configuration
	 */
	@SuppressWarnings("rawtypes")
	@Bean
	public JedisClientConfiguration getJedisClientConfiguration() {
		JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jedisPoolingClientConfigurationBuilder = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration
				.builder();
		GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
		genericObjectPoolConfig.setMaxTotal(maxTotal);
		genericObjectPoolConfig.setMaxIdle(maxIdle);
		genericObjectPoolConfig.setMinIdle(minIdle);
		return jedisPoolingClientConfigurationBuilder.poolConfig(genericObjectPoolConfig).build();
	}

	/**
	 * Gets the jedis connection factory.
	 *
	 * @return the jedis connection factory
	 */
	@Bean
	public JedisConnectionFactory getJedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(host);
		if (!password.isEmpty()) {
			redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
		}
		redisStandaloneConfiguration.setPort(port);
		return new JedisConnectionFactory(redisStandaloneConfiguration, getJedisClientConfiguration());
	}

	/**
	 * Redis template.
	 *
	 * @return the redis template
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(getJedisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		
		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

}
