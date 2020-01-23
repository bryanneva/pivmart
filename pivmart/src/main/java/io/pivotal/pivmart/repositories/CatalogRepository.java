package io.pivotal.pivmart.repositories;

import io.pivotal.pivmart.models.Catalog;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CatalogRepository {
    Mono<List<Catalog>> findAll();

    Mono<Catalog> findByKey(String catalogKey);
}
