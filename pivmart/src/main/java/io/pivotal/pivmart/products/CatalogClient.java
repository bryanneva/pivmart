package io.pivotal.pivmart.products;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.repositories.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class CatalogClient implements CatalogRepository {
    private final WebClient webClient;

    @Override
    public Flux<Catalog> findAll() {
        return webClient.get()
                .uri(URI.create("http://pivmart-product-api/catalogs"))
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Catalog.class);
    }

    @Override
    public Mono<Catalog> findByKey(String catalogKey) {
        return webClient.get()
                .uri(URI.create("http://pivmart-product-api/catalogs/" + catalogKey))
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Catalog.class);
    }
}
