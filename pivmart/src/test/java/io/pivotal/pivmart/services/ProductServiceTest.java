package io.pivotal.pivmart.services;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.repositories.CatalogRepository;
import io.pivotal.pivmart.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceTest {
    @MockBean
    private CatalogRepository catalogRepository;
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void getAll() {
        Product expectedProduct = Product.builder().name("Sony Walkman").build();
        when(productRepository.findAll()).thenReturn(Flux.just(expectedProduct));

        StepVerifier.create(productService.getAll())
                .assertNext(product -> assertThat(product).isEqualTo(expectedProduct))
                .verifyComplete();
    }

    @Test
    void getAll_returnsFallbackWhenProductRepositoryFails() {
        Flux<Product> fluxError = Flux.error(new WebClientResponseException(503, "Service Unavailable", null, null, null));
        when(productRepository.findAll()).thenReturn(fluxError);

        StepVerifier.create(productService.getAll())
                .verifyComplete();
    }

    @Test
    void getForCatalog() {
        Product expectedProduct = Product.builder().name("MC Hammer Pants").build();
        Catalog clothesCatalog = Catalog.builder().build();

        when(catalogRepository.findByKey(anyString())).thenReturn(Mono.just(clothesCatalog));
        when(productRepository.findAllByCatalog(clothesCatalog)).thenReturn(Flux.just(expectedProduct));

        StepVerifier.create(productService.getForCatalog("clothes"))
                .assertNext(product -> assertThat(product).isEqualTo(expectedProduct))
                .verifyComplete();
    }

    @Test
    void getForCatalog_returnsFallbackWhenProductRepositoryFails() {
        Flux<Product> fluxError = Flux.error(new WebClientResponseException(503, "Service Unavailable", null, null, null));
        Catalog clothesCatalog = Catalog.builder().build();

        when(catalogRepository.findByKey(anyString())).thenReturn(Mono.just(clothesCatalog));
        when(productRepository.findAllByCatalog(any())).thenReturn(fluxError);

        StepVerifier.create(productService.getForCatalog("clothes"))
                .verifyComplete();
    }

    @Test
    void getForCatalog_returnsFallbackWhenCatalogRepositoryFails() {
        Mono<Catalog> monoError = Mono.error(new WebClientResponseException(503, "Service Unavailable", null, null, null));

        when(catalogRepository.findByKey(anyString())).thenReturn(monoError);

        StepVerifier.create(productService.getForCatalog("clothes"))
                .verifyComplete();
    }
}
