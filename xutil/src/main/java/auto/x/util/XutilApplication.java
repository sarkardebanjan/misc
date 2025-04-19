package auto.x.util;

import auto.x.util.service.AutoRetweeter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import twitter4j.TwitterException;

@SpringBootApplication
public class XutilApplication {

	public static void main(String[] args) throws TwitterException, InterruptedException {
		ApplicationContext applicationContext = SpringApplication.run(XutilApplication.class, args);
		AutoRetweeter autoRetweeter = applicationContext.getBean(AutoRetweeter.class);
		autoRetweeter.retweetAll();

		//Exit Spring context when done
		SpringApplication.exit(applicationContext);
	}

}
