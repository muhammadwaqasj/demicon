package de.demicon.connector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@Configuration
public class SpringConfig {

    @Bean
    public Builder getWebClient() {
        return WebClient.builder().baseUrl("https://randomuser.me/");
    }
}
