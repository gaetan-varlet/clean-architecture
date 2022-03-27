package com.example.democleanarch.vin.controller.model;

import com.example.democleanarch.vin.model.Vin;

import lombok.Data;

@Data
public class VinDTO {

	private Integer id;
	private String chateau;
	private String appellation;
	private Double prix;

	public Vin toVin() {
		Vin vin = new Vin();
		vin.setId(id);
		vin.setChateau(chateau);
		vin.setAppellation(appellation);
		vin.setPrix(prix);
		return vin;
	}

	public static VinDTO toVinDTO(final Vin vin) {
		VinDTO vinDTO = new VinDTO();
		vinDTO.setId(vin.getId());
		vinDTO.setChateau(vin.getChateau());
		vinDTO.setAppellation(vin.getAppellation());
		vinDTO.setPrix(vin.getPrix());
		return vinDTO;
	}
}
