package com.seminar.kozmetickisalon;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
@EntityScan(basePackages = {"com.seminar.kozmetickisalon.Model"})
@EnableJpaRepositories (basePackages = {"com.seminar.kozmetickisalon.*"})
class KozmetickisalonApplicationTests {

	@Test
	void contextLoads() {
	}

}
