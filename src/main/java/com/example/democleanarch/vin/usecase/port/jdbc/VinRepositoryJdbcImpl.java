package com.example.democleanarch.vin.usecase.port.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import com.example.democleanarch.vin.model.Vin;
import com.example.democleanarch.vin.usecase.port.VinRepository;

import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.stereotype.Repository;

@Repository
public class VinRepositoryJdbcImpl implements VinRepository {

	@PersistenceContext
	private EntityManager entityManager;

	private static final String NO_DATE_SOURCE = "no data source";

	@Override
	public Vin create(Vin vin) {
		String request = "INSERT INTO vin (chateau, appellation, prix) VALUES (?, ?, ?)";
		DataSource dataSource = ((EntityManagerFactoryInfo) entityManager.getEntityManagerFactory()).getDataSource();
		if (dataSource != null) {
			try (
					Connection connection = dataSource.getConnection();
					PreparedStatement ps = connection.prepareStatement(request);) {
				ps.setString(1, vin.getChateau());
				ps.setString(2, vin.getAppellation());
				ps.setDouble(3, vin.getPrix());
				ps.executeUpdate();
				return findByChateau(vin.getChateau())
						.orElseThrow(() -> new JdbcException("Erreur lors de l'enregistrement du vin"));
			} catch (SQLException e) {
				throw new JdbcException(e.getMessage());
			}
		}
		throw new JdbcException(NO_DATE_SOURCE);
	}

	@Override
	public List<Vin> findAll() {
		String request = "SELECT * FROM vin";
		DataSource dataSource = ((EntityManagerFactoryInfo) entityManager.getEntityManagerFactory()).getDataSource();
		if (dataSource != null) {
			try (
					Connection connection = dataSource.getConnection();
					PreparedStatement ps = connection.prepareStatement(request);
					ResultSet rs = ps.executeQuery();) {
				List<Vin> vins = new ArrayList<>();
				while (rs.next()) {
					vins.add(mapResultSetToVin(rs));
				}
				return vins;
			} catch (SQLException e) {
				throw new JdbcException(e.getMessage());
			}
		}
		throw new JdbcException(NO_DATE_SOURCE);
	}

	@Override
	public Optional<Vin> findById(Integer id) {
		return Optional.empty();
	}

	@Override
	public Optional<Vin> findByChateau(String chateau) {
		String request = "SELECT * FROM vin WHERE chateau = ?";
		DataSource dataSource = ((EntityManagerFactoryInfo) entityManager.getEntityManagerFactory()).getDataSource();
		if (dataSource != null) {
			try (
					Connection connection = dataSource.getConnection();
					PreparedStatement ps = connection.prepareStatement(request);
					ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					return Optional.of(mapResultSetToVin(rs));
				}
				return Optional.empty();
			} catch (SQLException e) {
				throw new JdbcException(e.getMessage());
			}
		}
		throw new JdbcException(NO_DATE_SOURCE);
	}

	@Override
	public void deleteById(Integer id) {
		String request = "DELETE FROM vin WHERE id = ?";
		DataSource dataSource = ((EntityManagerFactoryInfo) entityManager.getEntityManagerFactory()).getDataSource();
		if (dataSource != null) {
			try (
					Connection connection = dataSource.getConnection();
					PreparedStatement ps = connection.prepareStatement(request);) {
				ps.setInt(1, id);
				ps.executeUpdate();
			} catch (SQLException e) {
				throw new JdbcException(e.getMessage());
			}
		}
		throw new JdbcException(NO_DATE_SOURCE);
	}

	private Vin mapResultSetToVin(ResultSet rs) throws SQLException {
		Vin vin = new Vin();
		vin.setId(rs.getInt("id"));
		vin.setAppellation(rs.getString("appellation"));
		vin.setChateau(rs.getString("chateau"));
		vin.setPrix(rs.getDouble("prix"));
		return vin;
	}

}