package com.example.democleanarch.vin.usecase.exception;

public class VinNotFoundException extends RuntimeException {

	public VinNotFoundException(final Integer id) {
		super("Le vin avec l'id " + id + " n'existe pas.");
	}

}
