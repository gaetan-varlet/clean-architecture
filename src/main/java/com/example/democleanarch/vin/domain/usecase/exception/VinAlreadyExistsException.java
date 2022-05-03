package com.example.democleanarch.vin.domain.usecase.exception;

public class VinAlreadyExistsException extends RuntimeException {

	public VinAlreadyExistsException(final String chateau) {
		super("Le chateau " + chateau + " exite déjà.");
	}

}
