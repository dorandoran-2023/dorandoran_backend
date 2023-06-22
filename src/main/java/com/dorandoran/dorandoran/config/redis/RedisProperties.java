package com.dorandoran.dorandoran.config.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Getter;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
	private final String host;
	private final int port;

	public RedisProperties(String host, int port) {
		this.host = host;
		this.port = port;
	}
}
