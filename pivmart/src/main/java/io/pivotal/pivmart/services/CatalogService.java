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
    private final ReactiveCircuitBreakerFactory circuitBreakerFactory;
    private final CatalogRepository catalogRepository;

    public Flux<Catalog> getAll() {
        return circuitBreakerFactory.create("catalogs")
                .run(catalogRepository.findAll(),
                        throwable -> Flux.empty());
    }
}
