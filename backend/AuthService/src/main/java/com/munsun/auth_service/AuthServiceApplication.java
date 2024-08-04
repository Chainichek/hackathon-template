package com.munsun.auth_service;

import com.munsun.auth_service.models.Account;
import com.munsun.auth_service.models.enums.Role;
import com.munsun.auth_service.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.UUID;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthServiceApplication implements CommandLineRunner {
	@Autowired
	private AccountRepository repository;

	@Override
	public void run(String... args) throws Exception {
		if(!repository.existsByLogin("admin")) {
			repository.save(new Account(UUID.randomUUID(), "admin", "admin12345", Role.ADMIN));
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
}