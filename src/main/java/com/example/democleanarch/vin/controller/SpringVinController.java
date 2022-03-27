package com.example.democleanarch.vin.controller;

import java.util.List;

import com.example.democleanarch.vin.controller.model.VinDTO;
import com.example.democleanarch.vin.usecase.exception.VinAlreadyExistsException;
import com.example.democleanarch.vin.usecase.exception.VinNotFoundException;
import com.example.democleanarch.vin.usecase.exception.VinValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("vin")
public class SpringVinController {

	private final VinController controller;

	@Autowired
	public SpringVinController(final VinController controller) {
		this.controller = controller;
	}

	@PostMapping
	public VinDTO create(@RequestBody final VinDTO vinDTO) {
		try {
			return controller.createVin(vinDTO);
		} catch (VinValidationException | VinAlreadyExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping
	public List<VinDTO> findAll() {
		return controller.getAllVins();
	}

	@GetMapping("{id}")
	public VinDTO getById(@PathVariable final Integer id) {
		try {
			return controller.getVin(id);
		} catch (VinNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
