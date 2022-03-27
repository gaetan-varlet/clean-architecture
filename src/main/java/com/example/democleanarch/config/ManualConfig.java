package com.example.democleanarch.config;

import com.example.democleanarch.vin.controller.VinController;
import com.example.democleanarch.vin.usecase.CreateVin;
import com.example.democleanarch.vin.usecase.DeleteVin;
import com.example.democleanarch.vin.usecase.FindVin;
import com.example.democleanarch.vin.usecase.port.VinRepository;

import org.springframework.stereotype.Component;

@Component
public class ManualConfig {

	private VinRepository vinRepository;

	public ManualConfig(final VinRepository vinRepository) {
		this.vinRepository = vinRepository;
	}

	public CreateVin createVin() {
		return new CreateVin(vinRepository);
	}

	public FindVin findVin() {
		return new FindVin(vinRepository);
	}

	public DeleteVin deleteVin() {
		return new DeleteVin(vinRepository);
	}

	public VinController vinController() {
		return new VinController(createVin(), findVin(), deleteVin());
	}

}
