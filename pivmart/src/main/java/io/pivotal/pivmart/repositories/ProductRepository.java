package io.pivotal.pivmart.repositories;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import reactor.core.publisher.Flux;

public interface ProductRepository {
    Flux<Product> findAllByCatalog(Catalog catalog);

    Flux<Product> findAll();
}
