package com.example.democleanarch.vin.domain.usecase;

import java.util.List;
import java.util.Optional;

import com.example.democleanarch.vin.domain.model.Vin;

public interface VinRepository {

	Vin create(Vin vin);

	List<Vin> findAll();

	Optional<Vin> findById(Integer id);

	Optional<Vin> findByChateau(String chateau);

	void deleteById(Integer id);

	void deleteAll();

}
