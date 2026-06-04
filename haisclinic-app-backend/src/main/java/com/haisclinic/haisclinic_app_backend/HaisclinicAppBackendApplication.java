package com.haisclinic.haisclinic_app_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class HaisclinicAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HaisclinicAppBackendApplication.class, args);
	}

}
