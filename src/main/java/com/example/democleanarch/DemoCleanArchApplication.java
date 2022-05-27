package com.example.democleanarch;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.example.democleanarch.vin.infra.port.jpa.JpaVinRepository;
import com.example.democleanarch.vin.infra.port.jpa.VinEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DemoCleanArchApplication implements CommandLineRunner {

	@Autowired
	private JpaVinRepository vinRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoCleanArchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<VinEntity> vins = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			vins.add(v(i));
		}
		log.info("DEBUT SAUVEGARDE");
		LocalTime t1 = LocalTime.now();
		vinRepository.saveAll(vins);
		LocalTime t2 = LocalTime.now();
		log.info("FIN SAUVEGARDE");
		log.info("durée en ms : " + ChronoUnit.MILLIS.between(t1, t2));

	}

	public VinEntity v(int i) {
		VinEntity v = new VinEntity();
		v.setChateau("toto" + i);
		v.setAppellation("appellation");
		v.setPrix(5.20);
		return v;
	}

}
