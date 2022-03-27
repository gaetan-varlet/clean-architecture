package com.example.democleanarch.vin.usecase.port.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.democleanarch.vin.model.Vin;

import lombok.Data;

@Entity
@Table(name = "vin")
@Data
public class VinEntity {

	@Id
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

	public static VinEntity toVinEntity(final Vin vin) {
		VinEntity entity = new VinEntity();
		entity.setId(vin.getId());
		entity.setChateau(vin.getChateau());
		entity.setAppellation(vin.getAppellation());
		entity.setPrix(vin.getPrix());
		return entity;
	}

}
