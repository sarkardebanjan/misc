package com.dvicorp.mypassion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@RefreshScope
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.dvicorp.mypassion.dao")
public class MyPassionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPassionApplication.class, args);
	}

}
