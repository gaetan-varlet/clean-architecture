
package com.example.democleanarch.vin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.democleanarch.vin.domain.usecase.exception.VinNotFoundException;
import com.example.democleanarch.vin.infra.controller.VinController;
import com.example.democleanarch.vin.infra.controller.model.VinDTO;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class VinControllerSpringTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VinController vinController;

	private static final String URL = "/vin";

	@BeforeEach
	void init() {
		VinDTO v = new VinDTO();
		v.setId(3);
		v.setChateau("chateau");
		Mockito.when(vinController.getAllVins()).thenReturn(List.of(v));
		Mockito.when(vinController.getVin(1)).thenThrow(new VinNotFoundException(1));
		Mockito.when(vinController.getVin(3)).thenReturn(v);
	}

	@Test
	void getAllVins() throws Exception {
		mockMvc.perform(get(URL))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)));
	}

	@Test
	void getVinByIdOk() throws Exception {
		mockMvc.perform(get(URL + "/3"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.chateau", Matchers.is("chateau")));
	}

	@Test
	void getVinByIdKp() throws Exception {
		mockMvc.perform(get(URL + "/1"))
				.andExpect(status().isNotFound());
	}

}
