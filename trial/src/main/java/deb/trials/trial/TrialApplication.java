package deb.trials.trial;

import deb.trials.trial.service.WebService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TrialApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(TrialApplication.class, args);
		WebService webService = configurableApplicationContext.getBean("webService", WebService.class);
		webService.doGet();
	}

}
