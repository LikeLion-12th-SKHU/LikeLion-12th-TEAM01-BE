package org.skhuton.fitpete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class FitpeteApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitpeteApplication.class, args);
	}

}
