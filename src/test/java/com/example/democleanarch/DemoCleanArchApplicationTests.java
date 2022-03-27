package com.example.democleanarch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoCleanArchApplicationTests {

	@Test
	void contextLoads() {
		assertThat(1 + 1).isEqualTo(2);
	}

}
