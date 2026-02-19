package com.tutorial.jdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tutorial.jdbc.service.UserService;

@SpringBootApplication
public class JdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.printAllUsers();
			userService.activateUser(2L, true);

			System.out.println("\nFinal state:");
			userService.printUserById(2L);
			userService.printAllUsers();
		};
	}

}
