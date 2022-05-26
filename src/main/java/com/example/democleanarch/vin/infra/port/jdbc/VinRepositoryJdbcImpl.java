package com.example.democleanarch.vin.infra.port.jdbc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.example.democleanarch.vin.domain.model.Vin;
import com.example.democleanarch.vin.domain.usecase.VinRepository;
import com.example.democleanarch.vin.infra.port.jpa.VinEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VinRepositoryJdbcImpl implements VinRepository {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public Vin create(Vin vin) {
		log.info("Création d'un vin dans VinRepositoryJdbcImpl");
		em.persist(VinEntity.toVinEntity(vin));
		return findByChateau(vin.getChateau())
				.orElseThrow(() -> new JdbcException("Erreur lors de l'enregistrement du vin"));
	}

	@Override
	public List<Vin> findAll() {
		TypedQuery<VinEntity> query = em.createNamedQuery("findAllVins", VinEntity.class);
		List<VinEntity> list = query.getResultList();
		return list.stream().map(VinEntity::toVin).collect(Collectors.toList());
	}

	@Override
	public Optional<Vin> findById(Integer id) {
		VinEntity vinEntity = em.find(VinEntity.class, id);
		return Optional.ofNullable(vinEntity == null ? null : vinEntity.toVin());
	}

	@Override
	public Optional<Vin> findByChateau(String chateau) {
		TypedQuery<VinEntity> query = em.createNamedQuery("vinbyChateau", VinEntity.class);
		query.setParameter(1, chateau);
		List<VinEntity> result = query.getResultList();
		if (result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.get(0).toVin());
	}

	@Transactional
	@Override
	public void deleteById(Integer id) {
		Query query = em.createNamedQuery("deleteVinById");
		query.setParameter(1, id);
		query.executeUpdate();
	}

	@Transactional
	@Override
	public void deleteAll() {
		Query query = em.createNamedQuery("deleteAllVins");
		query.executeUpdate();
	}

}