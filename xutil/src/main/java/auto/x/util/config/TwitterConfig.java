package auto.x.util.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;

@Configuration
public class TwitterConfig {

    @Value("${self.consumer.key}")
    private String consumerKey;

    @Value("${self.consumer.secret}")
    private String consumerSecret;

    @Value("${self.access.token}")
    private String accessToken;

    @Value("${self.access.token.secret}")
    private String accessTokenSecret;

    @Bean
    public Twitter twitter() {
        return Twitter.newBuilder()
                .oAuthConsumer(consumerKey, consumerSecret)
                .oAuthAccessToken(accessToken, accessTokenSecret)
                .build();
    }
}
