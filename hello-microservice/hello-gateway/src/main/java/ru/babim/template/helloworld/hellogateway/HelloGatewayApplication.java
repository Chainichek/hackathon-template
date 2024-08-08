package ru.babim.template.helloworld.hellogateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableDiscoveryClient
public class HelloGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloGatewayApplication.class, args);
	}
}
