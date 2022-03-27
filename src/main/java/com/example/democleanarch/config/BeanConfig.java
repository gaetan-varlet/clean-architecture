package com.example.democleanarch.config;

import com.example.democleanarch.vin.controller.VinController;
import com.example.democleanarch.vin.usecase.CreateVin;
import com.example.democleanarch.vin.usecase.DeleteVin;
import com.example.democleanarch.vin.usecase.FindVin;
import com.example.democleanarch.vin.usecase.port.VinRepository;

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
