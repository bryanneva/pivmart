package io.pivotal.pivmart.repositories;

import io.pivotal.pivmart.models.Catalog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CatalogRepository {
    Flux<Catalog> findAll();

    Mono<Catalog> findByKey(String catalogKey);
}
