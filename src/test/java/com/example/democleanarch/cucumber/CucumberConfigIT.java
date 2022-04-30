package com.example.democleanarch.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(//
		features = { "src/test/resources/cucumber" }, // répertoire avec features
		glue = { "com.example.democleanarch.cucumber.glue" },
		// classe Java qui vont lire les features et exécuter les tests
		plugin = { "pretty", "html:target/reports/cucumber/rapport.html", "json:target/cucumber.json",
				"usage:target/usage.jsonx", "junit:target/junit.xml" })
public class CucumberConfigIT {

}
