package com.example.democleanarch.config;

import com.example.democleanarch.vin.controller.VinController;
import com.example.democleanarch.vin.usecase.CreateVin;
import com.example.democleanarch.vin.usecase.DeleteVin;
import com.example.democleanarch.vin.usecase.FindVin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringInstantiation {

	private final Instantiation instantiation = new Instantiation();

	@Bean
	public CreateVin createVin() {
		return instantiation.createVin();
	}

	@Bean
	public FindVin findVin() {
		return instantiation.findVin();
	}

	@Bean
	public DeleteVin deleteVin() {
		return instantiation.deleteVin();
	}

	@Bean
	public VinController vinController() {
		return new VinController(createVin(), findVin(), deleteVin());
	}

}
