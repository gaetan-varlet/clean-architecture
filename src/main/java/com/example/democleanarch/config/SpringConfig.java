package com.example.democleanarch.config;

import com.example.democleanarch.vin.controller.VinController;
import com.example.democleanarch.vin.usecase.CreateVin;
import com.example.democleanarch.vin.usecase.DeleteVin;
import com.example.democleanarch.vin.usecase.FindVin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

	@Autowired
	private ManualConfig config;

	@Bean
	public CreateVin createVin() {
		return config.createVin();
	}

	@Bean
	public FindVin findVin() {
		return config.findVin();
	}

	@Bean
	public DeleteVin deleteVin() {
		return config.deleteVin();
	}

	@Bean
	public VinController vinController() {
		return config.vinController();
	}

}
