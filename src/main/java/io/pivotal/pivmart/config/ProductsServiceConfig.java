package io.pivotal.pivmart.config;

import io.pivotal.pivmart.products.CatalogGateway;
import io.pivotal.pivmart.products.ProductGateway;
import io.pivotal.pivmart.products.ProductsServiceCatalogGateway;
import io.pivotal.pivmart.products.ProductsServiceProductGateway;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("!local")
public class ProductsServiceConfig {
    @Bean
    public CatalogGateway catalogGateway(RestTemplate restTemplate, ProductsServiceProperties productsServiceProperties) {
        return new ProductsServiceCatalogGateway(restTemplate, productsServiceProperties);
    }

    @Bean
    public ProductGateway productGateway(RestTemplate restTemplate) {
        return new ProductsServiceProductGateway(restTemplate);
    }
}
