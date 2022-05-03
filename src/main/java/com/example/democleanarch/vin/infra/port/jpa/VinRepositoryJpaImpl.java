package com.example.democleanarch.vin.infra.port.jpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.democleanarch.vin.domain.model.Vin;
import com.example.democleanarch.vin.domain.usecase.VinRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VinRepositoryJpaImpl implements VinRepository {

	private JpaVinRepository repository;

	public VinRepositoryJpaImpl(JpaVinRepository repository) {
		this.repository = repository;
	}

	@Override
	public Vin create(Vin vin) {
		log.info("Création d'un vin dans VinRepositoryJpaImpl");
		VinEntity entity = repository.save(VinEntity.toVinEntity(vin));
		return entity.toVin();
	}

	@Override
	public List<Vin> findAll() {
		return repository.findAll().stream()
				.map(VinEntity::toVin)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Vin> findById(Integer id) {
		Optional<VinEntity> v = repository.findById(id);
		if (v.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(v.get().toVin());
		}
	}

	@Override
	public Optional<Vin> findByChateau(String chateau) {
		VinEntity v = repository.findByChateau(chateau);
		if (v == null) {
			return Optional.empty();
		} else {
			return Optional.of(v.toVin());
		}
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

}