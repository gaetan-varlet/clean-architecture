package com.example.democleanarch.vin.domain.model;

import lombok.Data;

@Data
public class Vin {

	private Integer id;
	private String chateau;
	private String appellation;
	private Double prix;

}
