package com.example.democleanarch.vin.domain.usecase;

import com.example.democleanarch.vin.domain.usecase.exception.VinNotFoundException;
import com.example.democleanarch.vin.domain.usecase.validator.VinValidator;

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
