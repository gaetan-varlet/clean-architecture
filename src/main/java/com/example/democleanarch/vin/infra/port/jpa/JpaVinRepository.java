package com.example.democleanarch.vin.infra.port.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaVinRepository extends JpaRepository<VinEntity, Integer> {

	VinEntity findByChateau(String chateau);

}