package com.dorandoran.dorandoran.config.nurigo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dorandoran.dorandoran.infra.nurigo.NurigoSmsService;

@Configuration
public class NurigoConfiguration {

	@Bean
	public NurigoSmsService nurigoSmsService(NurigoProperties properties) {
		return new NurigoSmsService(properties);
	}
}
