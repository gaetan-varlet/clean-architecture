package com.example.democleanarch.cucumber.glue;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.democleanarch.DemoCleanArchApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = DemoCleanArchApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestGlue {

	@Autowired
	private TestRestTemplate restTemplate;
	private ResponseEntity<String> response;

	@When("j'envoie la requete en GET sur l'url {string}")
	public void get(String url) throws Exception {
		response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
	}

	@When("j'envoie la requete en POST sur l'url {string} avec le contenu")
	public void post(String url, String content) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(content, headers);
		response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	}

	@Then("le code HTTP de la réponse est {int}")
	public void testResponseCode(int expectedStatus) {
		assertThat(response.getStatusCode().value()).isEqualTo(expectedStatus);
	}

	@Then("le contenu de la reponse doit etre strictement")
	public void testBody(String content) {
		assertThat(response.getBody()).isEqualTo(content);
	}

}
