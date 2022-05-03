package com.example.democleanarch.config;

import com.example.democleanarch.vin.domain.usecase.CreateVin;
import com.example.democleanarch.vin.domain.usecase.DeleteVin;
import com.example.democleanarch.vin.domain.usecase.FindVin;
import com.example.democleanarch.vin.domain.usecase.VinRepository;
import com.example.democleanarch.vin.infra.controller.VinController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	private VinRepository vinRepository;

	public BeanConfig(final VinRepository vinRepository) {
		this.vinRepository = vinRepository;
	}

	@Bean
	public CreateVin createVin() {
		return new CreateVin(vinRepository);
	}

	@Bean
	public FindVin findVin() {
		return new FindVin(vinRepository);
	}

	@Bean
	public DeleteVin deleteVin() {
		return new DeleteVin(vinRepository);
	}

	@Bean
	public VinController vinController() {
		return new VinController(createVin(), findVin(), deleteVin());
	}

}
