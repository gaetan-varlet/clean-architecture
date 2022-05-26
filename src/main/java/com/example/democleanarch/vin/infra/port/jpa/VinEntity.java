package com.example.democleanarch.vin.infra.port.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.democleanarch.vin.domain.model.Vin;

import lombok.Data;

@NamedNativeQuery(name = "vinbyChateau", query = "SELECT * FROM vin WHERE chateau = ?1", resultClass = VinEntity.class)
@NamedNativeQuery(name = "findAllVins", query = "SELECT * FROM vin", resultClass = VinEntity.class)
@NamedNativeQuery(name = "deleteAllVins", query = "DELETE FROM vin")
@NamedNativeQuery(name = "deleteVinById", query = "DELETE FROM vin WHERE id = ?1")
@NamedNativeQuery(name = "insertVin", query = "INSERT INTO vin (id, chateau, appellation, prix) VALUES (nextval('vin_id_seq'), ?1, ?2, ?3)")
@Entity
@Table(name = "vin")
@Data
public class VinEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vin")
	@SequenceGenerator(name = "seq_vin", sequenceName = "vin_id_seq", allocationSize = 1)
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
