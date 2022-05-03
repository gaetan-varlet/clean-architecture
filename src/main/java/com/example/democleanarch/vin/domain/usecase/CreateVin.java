package com.example.democleanarch.vin.domain.usecase;

import com.example.democleanarch.vin.domain.model.Vin;
import com.example.democleanarch.vin.domain.usecase.exception.VinAlreadyExistsException;
import com.example.democleanarch.vin.domain.usecase.validator.VinValidator;

public class CreateVin {

	private final VinRepository repository;

	public CreateVin(final VinRepository vinRepository) {
		this.repository = vinRepository;
	}

	public Vin create(final Vin vin) {
		VinValidator.validateCreateVin(vin);
		if (repository.findByChateau(vin.getChateau()).isPresent()) {
			throw new VinAlreadyExistsException(vin.getChateau());
		}
		Vin vinToSave = new Vin();
		// id doit rester null
		vinToSave.setChateau(vin.getChateau());
		vinToSave.setAppellation(vin.getAppellation());
		vinToSave.setPrix(vin.getPrix());
		return repository.create(vinToSave);
	}

}