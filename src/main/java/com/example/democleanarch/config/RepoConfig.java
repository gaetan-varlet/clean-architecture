package com.example.democleanarch.config;

import com.example.democleanarch.vin.domain.usecase.VinRepository;
import com.example.democleanarch.vin.infra.port.inmemory.VinRepositoryInMemoryImpl;
import com.example.democleanarch.vin.infra.port.jdbc.VinRepositoryJdbcImpl;
import com.example.democleanarch.vin.infra.port.jpa.JpaVinRepository;
import com.example.democleanarch.vin.infra.port.jpa.VinRepositoryJpaImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RepoConfig {

	@Value("${vin.repository}")
	private String vinRepository;

	@Bean
	public VinRepository getVinRepository(JpaVinRepository jpaVinRepository) {
		if (vinRepository == null) {
			throw new IllegalArgumentException("vinRepository should not be null");
		} else if (vinRepository.equals("jpa")) {
			log.info("implémentation VinRepositoryJpaImpl");
			return new VinRepositoryJpaImpl(jpaVinRepository);
		} else if (vinRepository.equals("jdbc")) {
			log.info("implémentation VinRepositoryJdbcImpl");
			return new VinRepositoryJdbcImpl();
		} else if (vinRepository.equals("inMemory")) {
			log.info("implémentation VinRepositoryInMemoryImpl");
			return new VinRepositoryInMemoryImpl();
		} else {
			throw new IllegalArgumentException("bad value for property");
		}
	}

}
