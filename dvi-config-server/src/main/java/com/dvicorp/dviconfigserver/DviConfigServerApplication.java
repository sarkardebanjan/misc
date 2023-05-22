package com.dvicorp.dviconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class DviConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DviConfigServerApplication.class, args);
	}

}
