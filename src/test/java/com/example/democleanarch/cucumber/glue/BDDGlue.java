package com.example.democleanarch.cucumber.glue;

import java.util.List;
import java.util.Map;

import com.example.democleanarch.vin.domain.model.Vin;
import com.example.democleanarch.vin.domain.usecase.VinRepository;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

public class BDDGlue {

	@Autowired
	private VinRepository repo;

	@Before
	public void viderBase() {
		repo.deleteAll();
	}

	@Given("la table {string} contient")
	public void alimentationTable(String table, DataTable dataTable) {
		List<Map<String, String>> dataAsMap = dataTable.asMaps();
		for (Map<String, String> ligne : dataAsMap) {
			Vin vin = new Vin();
			// vin.setId(Integer.valueOf(ligne.get("id")));
			vin.setChateau(ligne.get("chateau"));
			vin.setAppellation(ligne.get("appellation"));
			vin.setPrix(Double.valueOf(ligne.get("prix")));
			repo.create(vin);
		}
	}
}
