package com.example.democleanarch.vin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.democleanarch.conf.SpringBootTestDefaultConf;
import com.example.democleanarch.vin.controller.VinController;
import com.example.democleanarch.vin.controller.model.VinDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

public class VinControllerIT extends SpringBootTestDefaultConf {

	@Autowired
	private VinController vinController;

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	private static final String URL = "/vin";

	@BeforeEach
	void reset() {
		vinController.deleteAll();
	}

	@Test
	void doitAvoirAucunVin() throws Exception {
		mockMvc.perform(get(URL))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(0)));
	}

	@Test
	void doitCreerVinsPuiSupprimerVin() throws Exception {
		// il doit n'y avoir aucun vin avant la création
		mockMvc.perform(get(URL))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(0)));

		// création 1
		VinDTO toSave = new VinDTO();
		toSave.setChateau("chateau1");
		toSave.setAppellation("app");
		mockMvc.perform(post(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(toSave)))
				.andExpect(status().isOk());

		// il doit y avoir 1 vin
		mockMvc.perform(get(URL))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)));

		// création 2
		toSave = new VinDTO();
		toSave.setChateau("chateau2");
		toSave.setAppellation("app");
		mockMvc.perform(post(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(toSave)))
				.andExpect(status().isOk());

		// il doit y avoir 2 vin
		mockMvc.perform(get(URL))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)));
	}

	@Test
	void suppressionMultipleErreur() throws Exception {
		// il doit n'y avoir aucun vin avant la création
		mockMvc.perform(get(URL))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(0)));

		// création 1
		VinDTO toSave = new VinDTO();
		toSave.setChateau("chateau1");
		toSave.setAppellation("app");
		Integer id1 = vinController.createVin(toSave).getId();
		// création 2
		toSave.setChateau("chateau2");
		vinController.createVin(toSave);

		// il doit y avoir 2 vins après la création
		mockMvc.perform(get(URL))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)));

		// tentative de suppression de 2 vins
		mockMvc.perform(delete(URL + "/multiple" + "?id=" + id1 + "&id=5000"))
				.andExpect(status().isNotFound());

		// il doit toujours y avoir 2 vin car le second était en erreur
		mockMvc.perform(get(URL))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2)));
	}

	@Test
	void doitEncoreAvoirAucunVin2() throws Exception {
		mockMvc.perform(get(URL))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(0)));
	}

}
