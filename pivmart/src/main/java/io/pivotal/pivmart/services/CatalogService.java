package io.pivotal.pivmart.services;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.repositories.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogRepository catalogRepository;
    private final ReactiveCircuitBreakerFactory circuitBreaker;

    public Flux<Catalog> getAll() {
        return circuitBreaker.create("getAllCatalogs")
                .run(catalogRepository.findAll(),
                        throwable -> Flux.empty());
    }
}
