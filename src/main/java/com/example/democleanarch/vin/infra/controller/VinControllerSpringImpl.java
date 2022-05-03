package com.example.democleanarch.vin.infra.controller;

import java.util.List;

import javax.transaction.Transactional;

import com.example.democleanarch.vin.domain.usecase.exception.VinAlreadyExistsException;
import com.example.democleanarch.vin.domain.usecase.exception.VinNotFoundException;
import com.example.democleanarch.vin.domain.usecase.exception.VinValidationException;
import com.example.democleanarch.vin.infra.controller.model.VinDTO;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("vin")
@Transactional
public class VinControllerSpringImpl {

	private final VinController controller;

	public VinControllerSpringImpl(final VinController controller) {
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

	@DeleteMapping("multiple")
	public void delete(@RequestParam List<Integer> id) {
		try {
			controller.deleteVin(id);
		} catch (VinNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping
	public void deleteAll() {
		controller.deleteAll();
	}

}
