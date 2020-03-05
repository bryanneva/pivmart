package io.pivotal.pivmart.services;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.repositories.CatalogRepository;
import io.pivotal.pivmart.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CatalogRepository catalogRepository;
    private final ProductRepository productRepository;
    private final ReactiveCircuitBreakerFactory circuitBreakerFactory;

    public Flux<Product> getForCatalog(String catalogKey) {
        Catalog catalog = circuitBreakerFactory.create("getCatalog")
                .run(catalogRepository.findByKey(catalogKey),
                        throwable -> Mono.empty())
                .block();

        if (catalog != null) {
            return circuitBreakerFactory.create("productsForCatalog")
                    .run(productRepository.findAllByCatalog(catalog),
                            throwable -> Flux.empty());
        } else {
            return Flux.empty();
        }

    }

    public Flux<Product> getAll() {
        return circuitBreakerFactory.create("allProducts")
                .run(productRepository.findAll(),
                        throwable -> Flux.empty());
    }
}
