package io.pivotal.pivmart.services;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.repositories.CatalogRepository;
import io.pivotal.pivmart.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    ProductService productService;

    @MockBean
    ProductRepository productRepository;

    @MockBean
    CatalogRepository catalogRepository;

    @Test
    void getAll() {
        productService.getAll();

        verify(productRepository).findAll();
    }

    @Test
    void getForCatalog__returnsAllProductsForACatalog() {
        Product expectedProduct = Product.builder().name("Switch").build();
        Catalog catalog = Catalog.builder().catalogKey("electronics").build();

        when(catalogRepository.findByKey("electronics")).thenReturn(Mono.just(catalog));
        when(productRepository.findAllByCatalog(catalog)).thenReturn(Flux.just(expectedProduct));

        StepVerifier.create(productService.getForCatalog("electronics"))
                .assertNext(product -> {
                    assertThat(product).isEqualTo(expectedProduct);
                })
                .verifyComplete();
    }

    @Test
    void getForCatalog__returnsNoProductsWhenCatalogServiceFails() {
        when(catalogRepository.findByKey("electronics")).thenReturn(Mono.error(new WebClientResponseException(503, "Service Unavailable", null, null, null)));
//        when(productRepository.findAllByCatalog(catalog)).thenReturn(Flux.just(expectedProduct));

        StepVerifier.create(productService.getForCatalog("electronics"))
                .verifyComplete();
    }
}
