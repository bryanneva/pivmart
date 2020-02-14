package io.pivotal.pivmart.products;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.repositories.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class CatalogClient implements CatalogRepository {
    private final WebClient webClient;

    @Override
    public List<Catalog> findAll() {
        ParameterizedTypeReference<List<Catalog>> expectedType = new ParameterizedTypeReference<List<Catalog>>() {
        };
        return webClient.get()
                .uri(URI.create("http://product-api/catalogs"))
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(expectedType)
                .block();
    }

    @Override
    public Catalog findByKey(String catalogKey) {
        return webClient.get()
                .uri(URI.create("http://product-api/catalogs/" + catalogKey))
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Catalog.class)
                .block();
    }
}
