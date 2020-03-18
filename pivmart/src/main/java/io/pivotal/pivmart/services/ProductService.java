package io.pivotal.pivmart.services;

import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.repositories.CatalogRepository;
import io.pivotal.pivmart.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CatalogRepository catalogRepository;
    private final ProductRepository productRepository;
    private final ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;

    public Flux<Product> getForCatalog(String catalogKey) {


        return reactiveCircuitBreakerFactory.create("getProductsByCatalog")
                .run(catalogRepository
                                .findByKey(catalogKey)
                                .flatMapMany(catalog -> productRepository.findAllByCatalog(catalog)),
                        throwable -> Flux.empty()
                );
    }

    public Flux<Product> getAll() {
        return productRepository.findAll();
    }
}
