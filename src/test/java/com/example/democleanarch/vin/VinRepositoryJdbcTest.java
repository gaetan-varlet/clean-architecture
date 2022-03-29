
package com.example.democleanarch.vin;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import com.example.democleanarch.conf.SpringBootTestDefaultConf;
import com.example.democleanarch.vin.model.Vin;
import com.example.democleanarch.vin.usecase.port.VinRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "classpath:application.properties", properties = {
		"vin.repository=jdbc"
})
public class VinRepositoryJdbcTest extends SpringBootTestDefaultConf {

	@Autowired
	private VinRepository repo;

	@Test
	void enchainement() {
		// base vide au départ
		List<Vin> vins = repo.findAll();
		assertThat(vins).isEmpty();

		// création d'un vin
		Vin toSave = new Vin();
		toSave.setChateau("chateau1");
		toSave.setAppellation("app");
		Vin saved = repo.create(toSave);

		// il y a voir 1 vin
		vins = repo.findAll();
		assertThat(vins).hasSize(1);
		assertThat(vins.get(0).getChateau()).isEqualTo("chateau1");

		// récupération du vin par l'id
		Optional<Vin> opt = repo.findById(saved.getId());
		assertThat(opt).isNotEmpty();
		assertThat(opt.get().getChateau()).isEqualTo("chateau1");
		assertThat(opt.get().getAppellation()).isEqualTo("app");
		assertThat(opt.get().getPrix()).isNull();

		// récupération du vin par le chateau
		opt = repo.findByChateau("chateau1");
		assertThat(opt).isNotEmpty();
		assertThat(opt.get().getChateau()).isEqualTo("chateau1");
		assertThat(opt.get().getAppellation()).isEqualTo("app");
		assertThat(opt.get().getPrix()).isNull();

		// suppression d'un vin par son id
		repo.deleteById(saved.getId());

		// il doit y avoir 0 vins ensuite
		vins = repo.findAll();
		assertThat(vins).isEmpty();
	}
}
