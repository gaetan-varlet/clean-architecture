package com.example.democleanarch.config;

import com.example.democleanarch.vin.usecase.CreateVin;
import com.example.democleanarch.vin.usecase.DeleteVin;
import com.example.democleanarch.vin.usecase.FindVin;
import com.example.democleanarch.vin.usecase.port.VinRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Instantiation {

	@Autowired
	@Qualifier("vinRepositoryImpl")
	private VinRepository vinRepository;

	public CreateVin createVin() {
		return new CreateVin(vinRepository);
	}

	public FindVin findVin() {
		return new FindVin(vinRepository);
	}

	public DeleteVin deleteVin() {
		return new DeleteVin(vinRepository);
	}

}
