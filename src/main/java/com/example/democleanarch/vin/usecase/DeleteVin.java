package com.example.democleanarch.vin.usecase;

import com.example.democleanarch.vin.usecase.exception.VinNotFoundException;
import com.example.democleanarch.vin.usecase.port.VinRepository;
import com.example.democleanarch.vin.usecase.validator.VinValidator;

import org.springframework.stereotype.Component;

@Component
public class DeleteVin {

	private final VinRepository repository;

	public DeleteVin(final VinRepository vinRepository) {
		this.repository = vinRepository;
	}

	public String deleteById(Integer id) {
		VinValidator.validateVinId(id);
		if (repository.findById(id).isEmpty()) {
			throw new VinNotFoundException(id);
		}
		repository.deleteById(id);
		return "Le vin " + id + " est supprimé.";
	}

}
