package com.dvicorp.dviconfigclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
public class DviConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DviConfigClientApplication.class, args);
		while(true) {}
	}

}
