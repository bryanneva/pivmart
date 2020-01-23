package io.pivotal.pivmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class PivmartApplication {

    public static void main(String[] args) {
        SpringApplication.run(PivmartApplication.class, args);
    }


	@Profile("default")
	@Configuration
	public static class Default {

		@Bean
		@Primary
		public WebClient loadBalancedRestTemplate() {

			return WebClient.builder().build();
		}

	}

	@Profile("cloud")
	@Configuration
	public static class Cloud {

		@Bean
		@LoadBalanced
		@Primary
		public WebClient loadBalancedRestTemplate() {

			return WebClient.builder().build();
		}

	}

}
