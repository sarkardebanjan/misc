package deb.trials.trial.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Value("${base.uri:https://api.restful-api.dev}")
    private String baseUri;

    @Value("${response.timeout.in.millis:10000}")
    private long responseTimeoutInMillis;

    @Bean("webClient")
    public WebClient webClient() {
        HttpClient httpClient = HttpClient.create().responseTimeout(Duration.ofMillis(responseTimeoutInMillis));
        return WebClient.builder().baseUrl(baseUri).clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }
}
