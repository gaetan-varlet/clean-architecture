package com.example.democleanarch.vin.usecase.validator;

import com.example.democleanarch.vin.model.Vin;
import com.example.democleanarch.vin.usecase.exception.VinValidationException;

import org.apache.commons.lang3.StringUtils;

public class VinValidator {

	private VinValidator() {

	}

	public static void validateVinId(final Integer id) {
		if (id == null) {
			throw new VinValidationException("id should not be null");
		}
	}

	public static void validateCreateVin(final Vin vin) {
		if (vin == null) {
			throw new VinValidationException("vin should not be null");
		}
		if (StringUtils.isBlank(vin.getChateau())) {
			throw new VinValidationException("chateau should not be null");
		}
		if (vin.getChateau().length() > 50) {
			throw new VinValidationException("chateau should not be more than 50 caracters");
		}
		if (StringUtils.isBlank(vin.getAppellation())) {
			throw new VinValidationException("appelation should not be null");
		}
		if (vin.getPrix() != null && vin.getPrix() < 0) {
			throw new VinValidationException("prix should not be negative");
		}
	}

}
