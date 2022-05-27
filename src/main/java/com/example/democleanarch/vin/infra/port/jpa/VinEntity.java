package com.example.democleanarch.vin.infra.port.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.democleanarch.vin.domain.model.Vin;

import lombok.Data;

@NamedQuery(name = "vinbyChateau", query = "FROM VinEntity v WHERE v.chateau = ?1")
@NamedQuery(name = "findAllVins", query = "FROM VinEntity")
@NamedQuery(name = "deleteAllVins", query = "DELETE FROM VinEntity")
@NamedQuery(name = "deleteVinById", query = "DELETE FROM VinEntity v WHERE v.id = ?1")
@Entity
@Table(name = "vin")
@Data
public class VinEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vin")
	@SequenceGenerator(name = "seq_vin", sequenceName = "vin_id_seq", allocationSize = 100)
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
