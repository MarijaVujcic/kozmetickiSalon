package com.seminar.kozmetickisalon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.seminar.kozmetickisalon")
@SpringBootApplication
@EntityScan("com.seminar.kozmetickisalon.Model.*")
@EnableJpaRepositories("com.seminar.kozmetickisalon.Repository.*")
public class KozmetickisalonApplication {

	public static void main(String[] args) {
		SpringApplication.run(KozmetickisalonApplication.class, args);
	}

}
