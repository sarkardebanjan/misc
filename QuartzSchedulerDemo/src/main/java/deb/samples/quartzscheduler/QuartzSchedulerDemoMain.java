package deb.samples.quartzscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QuartzSchedulerDemoMain {

	public static void main(String[] args) {
		SpringApplication.run(QuartzSchedulerDemoMain.class, args);
	}

}
