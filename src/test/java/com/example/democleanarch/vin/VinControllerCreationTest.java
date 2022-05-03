package com.example.democleanarch.vin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import com.example.democleanarch.config.BeanConfig;
import com.example.democleanarch.vin.domain.usecase.exception.VinValidationException;
import com.example.democleanarch.vin.infra.controller.VinController;
import com.example.democleanarch.vin.infra.controller.model.VinDTO;
import com.example.democleanarch.vin.infra.port.inmemory.VinRepositoryInMemoryImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VinControllerCreationTest {

	private BeanConfig config = new BeanConfig(new VinRepositoryInMemoryImpl());
	private VinController vinController = config.vinController();

	@BeforeEach
	void reset() {
		vinController.deleteAll();
	}

	@Test
	void doitRenseignerChateau() {
		// il doit n'y avoir aucun vin avant la création
		List<VinDTO> vins = vinController.getAllVins();
		assertThat(vins).isEmpty();

		VinDTO toSave = new VinDTO();
		toSave.setAppellation("app");
		// ok car au moins 1 caractère non vide
		toSave.setChateau("1");
		vinController.createVin(toSave);
		// ok car limite de 50 caractères
		toSave.setChateau("12345678901234567890123456789012345678901234567890");
		vinController.createVin(toSave);

		// erreur
		toSave.setChateau(null);
		assertThatThrownBy(() -> vinController.createVin(toSave))
				.isInstanceOf(VinValidationException.class)
				.hasMessage("chateau should not be null");

		toSave.setChateau("");
		assertThatThrownBy(() -> vinController.createVin(toSave))
				.isInstanceOf(VinValidationException.class)
				.hasMessage("chateau should not be null");

		toSave.setChateau("  ");
		assertThatThrownBy(() -> vinController.createVin(toSave))
				.isInstanceOf(VinValidationException.class)
				.hasMessage("chateau should not be null");

		toSave.setChateau("123456789012345678901234567890123456789012345678901");
		assertThatThrownBy(() -> vinController.createVin(toSave))
				.isInstanceOf(VinValidationException.class)
				.hasMessage("chateau should not be more than 50 caracters");

		vins = vinController.getAllVins();
		assertThat(vins).hasSize(2);
	}

	@Test
	void doitRenseignerAppellation() {
		// il doit n'y avoir aucun vin avant la création
		List<VinDTO> vins = vinController.getAllVins();
		assertThat(vins).isEmpty();

		VinDTO toSave = new VinDTO();
		toSave.setChateau("chateau");
		// ok car au moins 1 caractère non vide
		toSave.setAppellation("a");
		vinController.createVin(toSave);

		// erreur
		toSave.setAppellation(null);
		assertThatThrownBy(() -> vinController.createVin(toSave))
				.isInstanceOf(VinValidationException.class)
				.hasMessage("appelation should not be null");

		toSave.setAppellation("");
		assertThatThrownBy(() -> vinController.createVin(toSave))
				.isInstanceOf(VinValidationException.class)
				.hasMessage("appelation should not be null");

		toSave.setAppellation("  ");
		assertThatThrownBy(() -> vinController.createVin(toSave))
				.isInstanceOf(VinValidationException.class)
				.hasMessage("appelation should not be null");

		vins = vinController.getAllVins();
		assertThat(vins).hasSize(1);
	}

}
