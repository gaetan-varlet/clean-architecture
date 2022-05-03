package com.example.democleanarch.vin.infra.port.jdbc;

public class JdbcException extends RuntimeException {
	public JdbcException(final String message) {
		super(message);
	}
}
