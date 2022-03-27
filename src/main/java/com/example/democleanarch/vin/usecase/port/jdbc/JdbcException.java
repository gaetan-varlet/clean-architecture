package com.example.democleanarch.vin.usecase.port.jdbc;

public class JdbcException extends RuntimeException {
	public JdbcException(final String message) {
		super(message);
	}
}
