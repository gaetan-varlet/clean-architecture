package com.example.democleanarch.vin.usecase.port.jpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.democleanarch.vin.model.Vin;
import com.example.democleanarch.vin.usecase.port.VinRepository;

import org.springframework.stereotype.Component;

@Component
public class VinRepositoryImpl implements VinRepository {

	private JpaVinRepository repository;

	public VinRepositoryImpl(JpaVinRepository repository) {
		this.repository = repository;
	}

	@Override
	public Vin create(Vin vin) {
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

}