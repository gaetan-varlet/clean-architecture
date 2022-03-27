package com.example.democleanarch.vin.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.democleanarch.vin.controller.model.VinDTO;
import com.example.democleanarch.vin.model.Vin;
import com.example.democleanarch.vin.usecase.CreateVin;
import com.example.democleanarch.vin.usecase.DeleteVin;
import com.example.democleanarch.vin.usecase.FindVin;
import com.example.democleanarch.vin.usecase.exception.VinNotFoundException;

import org.springframework.stereotype.Component;

@Component
public class VinController {

	private CreateVin createVin;
	private FindVin findVin;
	private DeleteVin deleteVin;

	public VinController(final CreateVin createVin, final FindVin findVin, final DeleteVin deleteVin) {
		this.createVin = createVin;
		this.findVin = findVin;
		this.deleteVin = deleteVin;
	}

	public VinDTO createVin(final VinDTO vinDTO) {
		Vin vin = vinDTO.toVin();
		return VinDTO.toVinDTO(createVin.create(vin));
	}

	public VinDTO getVin(final Integer id) {
		return VinDTO.toVinDTO(findVin.findById(id).orElseThrow(() -> new VinNotFoundException(id)));
	}

	public List<VinDTO> getAllVins() {
		return findVin.findAllVin()
				.stream()
				.map(VinDTO::toVinDTO)
				.collect(Collectors.toList());
	}

	public String deleteVin(final Integer id) {
		return deleteVin.deleteById(id);
	}

	public void deleteAll() {
		List<Vin> vins = findVin.findAllVin();
		for (Vin v : vins) {
			deleteVin.deleteById(v.getId());
		}
	}

}
