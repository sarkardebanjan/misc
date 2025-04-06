package deb.camel.transactions.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;

@SpringBootApplication(exclude = { JmsAutoConfiguration.class, DataSourceAutoConfiguration.class })
public class CamelTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelTransactionsApplication.class, args);
	}

}
