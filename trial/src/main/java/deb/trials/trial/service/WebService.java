package deb.trials.trial.service;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class WebService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebService.class);

    @Resource(name =  "webClient")
    private WebClient webClient;

    public void doGet() {
        String path = "/objects";

        String body = "{\n" +
                "   \"name\": \"Apple MacBook Pro 16\",\n" +
                "   \"data\": {\n" +
                "      \"year\": 2019,\n" +
                "      \"price\": 1849.99,\n" +
                "      \"CPU model\": \"Intel Core i9\",\n" +
                "      \"Hard disk size\": \"1 TB\"\n" +
                "   }\n" +
                "}";

        webClient.post()
                .uri(path)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .toEntity(String.class)
                //.toBodilessEntity()
                .retryWhen(Retry.fixedDelay(5, Duration.ofMillis(3000)).doBeforeRetry(signal -> LOGGER.info("Retry attempt no: {}", signal.totalRetries() + 1)))
                .subscribe(success -> handleSuccess(success), error -> handleFailure(error));

    }

    private void handleSuccess(ResponseEntity<String> success) {
        LOGGER.info("POST call was successful");
    }

    private void handleFailure(Throwable error) {
        LOGGER.error("POST call failed. Error: {}", error.getMessage(), error);
    }

}
