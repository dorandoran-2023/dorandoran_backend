package com.dorandoran.dorandoran.config.redis;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Configuration;

import redis.embedded.RedisServer;

@Configuration
public class EmbeddedRedisConfiguration {
	private final RedisServer redisServer;

	public EmbeddedRedisConfiguration(RedisProperties properties) {
		this.redisServer = new RedisServer(properties.getPort());
	}

	@PostConstruct
	public void postConstruct() {
		redisServer.start();
	}

	@PreDestroy
	public void preDestroy() {
		if (redisServer != null) {
			redisServer.stop();
		}
	}
}
