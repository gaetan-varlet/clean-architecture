package com.example.democleanarch.vin.usecase;

import java.util.List;
import java.util.Optional;

import com.example.democleanarch.vin.model.Vin;
import com.example.democleanarch.vin.usecase.port.VinRepository;
import com.example.democleanarch.vin.usecase.validator.VinValidator;

import org.springframework.stereotype.Component;

@Component
public class FindVin {

	private final VinRepository repository;

	public FindVin(final VinRepository vinRepository) {
		this.repository = vinRepository;
	}

	public List<Vin> findAllVin() {
		return repository.findAll();
	}

	public Optional<Vin> findById(Integer id) {
		VinValidator.validateVinId(id);
		return repository.findById(id);
	}

}
