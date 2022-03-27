package com.example.democleanarch.vin.usecase.port.inmemory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.democleanarch.vin.model.Vin;
import com.example.democleanarch.vin.usecase.port.VinRepository;

public class InMemoryVinRepository implements VinRepository {

	private Integer currentId = 0;
	private final Map<Integer, Vin> inMemoryDb = new HashMap<>();

	@Override
	public Vin create(Vin vin) {
		vin.setId(currentId++);
		inMemoryDb.put(vin.getId(), vin);
		return vin;
	}

	@Override
	public List<Vin> findAll() {
		return new ArrayList<>(inMemoryDb.values());
	}

	@Override
	public Optional<Vin> findById(Integer id) {
		return Optional.ofNullable(inMemoryDb.get(id));
	}

	@Override
	public Optional<Vin> findByChateau(String chateau) {
		return inMemoryDb.values().stream()
				.filter(v -> v.getChateau().equals(chateau))
				.findAny();
	}

	@Override
	public void deleteById(Integer id) {
		inMemoryDb.remove(id);
	}

}
