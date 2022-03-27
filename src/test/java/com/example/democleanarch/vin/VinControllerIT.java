package com.example.democleanarch.vin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.democleanarch.conf.SpringBootTestDefaultConf;
import com.example.democleanarch.vin.controller.VinController;
import com.example.democleanarch.vin.controller.model.VinDTO;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class VinControllerIT extends SpringBootTestDefaultConf {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private VinController vinController;

	@BeforeEach
	void reset() {
		vinController.deleteAll();
	}

	@Test
	void test() throws Exception {
		mockMvc.perform(get("/vin"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(0)));
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
	void doitEncoreAvoirAucunVin2() {
		List<VinDTO> vins = vinController.getAllVins();
		assertThat(vins).isEmpty();
	}

}
