package com.example.democleanarch.vin.domain.usecase.exception;

public class VinValidationException extends RuntimeException {

	public VinValidationException(final String message) {
		super(message);
	}

}
