package io.pivotal.pivmart.services;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.repositories.CatalogRepository;
import io.pivotal.pivmart.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CatalogRepository catalogRepository;
    private final ProductRepository productRepository;
    private final ReactiveCircuitBreakerFactory circuitBreakerFactory;

    public Flux<Product> getForCatalog(String catalogKey) {
        Catalog catalog;
        try {
            catalog = catalogRepository.findByKey(catalogKey).block();
        } catch (WebClientResponseException e) {
            return Flux.empty();
        }

        return circuitBreakerFactory.create("productsForCatalog")
                .run(productRepository.findAllByCatalog(catalog),
                        throwable -> Flux.empty());
    }

    public Flux<Product> getAll() {
        return circuitBreakerFactory.create("allProducts")
                .run(productRepository.findAll(),
                        throwable -> Flux.empty());
    }
}
