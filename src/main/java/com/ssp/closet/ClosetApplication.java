package com.ssp.closet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ClosetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClosetApplication.class, args);
	}

}
