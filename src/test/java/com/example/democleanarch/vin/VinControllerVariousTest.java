package com.example.democleanarch.vin;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.example.democleanarch.vin.controller.VinController;
import com.example.democleanarch.vin.controller.model.VinDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VinControllerVariousTest {

	@Autowired
	private VinController vinController;

	@BeforeEach
	void reset() {
		vinController.deleteAll();
	}

	@Test
	void doitAvoirAucunVin() {
		List<VinDTO> vins = vinController.getAllVins();
		assertThat(vins).isEmpty();
	}

	@Test
	void doitCreerVinsPuiSupprimerVin() {
		// il doit n'y avoir aucun vin avant la création
		List<VinDTO> vins = vinController.getAllVins();
		assertThat(vins).isEmpty();

		// création 1
		VinDTO toSave = new VinDTO();
		toSave.setChateau("chateau1");
		toSave.setAppellation("app");
		Integer firstId = vinController.createVin(toSave).getId();

		// il doit y avoir 1 vin
		vins = vinController.getAllVins();
		assertThat(vins).hasSize(1);

		// création 2
		toSave = new VinDTO();
		toSave.setChateau("chateau2");
		toSave.setAppellation("app");
		VinDTO createdVin = vinController.createVin(toSave);

		// il doit y avoir 2 vin
		vins = vinController.getAllVins();
		assertThat(vins).hasSize(2);

		// supprression du 2e vin créé
		vinController.deleteVin(createdVin.getId());

		// il doit y avoir 1 vin
		vins = vinController.getAllVins();
		assertThat(vins).hasSize(1);
		assertThat(vins.get(0).getId()).isEqualTo(firstId);
	}

	@Test
	void doitProutAvoirAucunVin2() {
		List<VinDTO> vins = vinController.getAllVins();
		assertThat(vins).isEmpty();
	}

}
