package com.magalu.logistica.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AgendamentoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendamentoAppApplication.class, args);
	}

}
