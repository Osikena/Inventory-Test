package com.mega.amps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan(basePackages = "com.mega.amps.domain")
public class LiquibaseSqliteApplication {

	public static void main(String[] args) {

		SpringApplication.run(LiquibaseSqliteApplication.class, args);
	}

}
