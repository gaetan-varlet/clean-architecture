package com.example.democleanarch;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.democleanarch.conf.SpringBootTestDefaultConf;

import org.junit.jupiter.api.Test;

class DemoCleanArchApplicationTests extends SpringBootTestDefaultConf {

	@Test
	void contextLoads() {
		assertThat(1 + 1).isEqualTo(2);
	}

}
