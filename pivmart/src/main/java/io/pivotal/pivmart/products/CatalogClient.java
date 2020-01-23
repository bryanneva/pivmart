package io.pivotal.pivmart.products;

import io.pivotal.pivmart.config.ProductApiProperties;
import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.repositories.CatalogRepository;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class CatalogClient implements CatalogRepository {
    private WebClient webClient;
    private RestTemplate restTemplate;
    private ProductApiProperties productApiProperties;

    public CatalogClient(RestTemplate restTemplate, ProductApiProperties productApiProperties) {
        this.restTemplate = restTemplate;
        this.productApiProperties = productApiProperties;
        this.webClient = WebClient.builder().baseUrl(productApiProperties.getUrl() + "/catalogs").build();
    }

    @Override
    public List<Catalog> findAll() {
        ParameterizedTypeReference<List<Catalog>> expectedType = new ParameterizedTypeReference<List<Catalog>>() {
        };
        return webClient.get()
                .uri("")
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(expectedType)
                .block();
    }

    @Override
    public Catalog findByKey(String catalogKey) {
        ResponseEntity<Catalog> response = restTemplate.exchange(
                URI.create(productApiProperties.getUrl() + "catalogs/" + catalogKey),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Catalog>() {
                }
        );

        return response.getBody();
    }
}
