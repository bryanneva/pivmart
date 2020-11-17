package io.pivotal.pivmart.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced
    WebClient.Builder webClientBuilder() {

        return WebClient.builder();
    }

    @Bean
    WebClient webClient( final WebClient.Builder builder ) {

        return builder.build();
    }


}
