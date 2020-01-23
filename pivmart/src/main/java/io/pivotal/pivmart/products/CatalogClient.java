package io.pivotal.pivmart.products;

import io.pivotal.pivmart.config.ProductApiProperties;
import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.repositories.CatalogRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class CatalogClient implements CatalogRepository {
    private WebClient webClient;
    private ProductApiProperties productApiProperties;

    public CatalogClient(ProductApiProperties productApiProperties) {
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
        return webClient.get()
                .uri(URI.create(productApiProperties.getUrl() + "catalogs/" + catalogKey))
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Catalog.class)
                .block();
    }
}
