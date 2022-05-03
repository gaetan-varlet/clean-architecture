package com.example.democleanarch.vin.infra.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.example.democleanarch.vin.domain.model.Vin;
import com.example.democleanarch.vin.domain.usecase.CreateVin;
import com.example.democleanarch.vin.domain.usecase.DeleteVin;
import com.example.democleanarch.vin.domain.usecase.FindVin;
import com.example.democleanarch.vin.domain.usecase.exception.VinNotFoundException;
import com.example.democleanarch.vin.infra.controller.model.VinDTO;

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
		return findVin.findAllVin().stream()
				.map(VinDTO::toVinDTO)
				.collect(toList());
	}

	public String deleteVin(final Integer id) {
		return deleteVin.deleteById(id);
	}

	public List<String> deleteVin(final List<Integer> ids) {
		return ids.stream().map(this::deleteVin).collect(toList());
	}

	public void deleteAll() {
		List<Vin> vins = findVin.findAllVin();
		for (Vin v : vins) {
			deleteVin.deleteById(v.getId());
		}
	}

}
