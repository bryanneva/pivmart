package io.pivotal.pivmart.products;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.repositories.CatalogRepository;
import io.pivotal.pivmart.services.CatalogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class CatalogServiceTest {
    @Autowired
    CatalogService catalogService;

    @MockBean
    CatalogRepository catalogRepository;

    @Test
    void getAll_returnsResultsFromRepository() {
        Catalog expectedCatalog = Catalog.builder().catalogKey("electronics").build();

        when(catalogRepository.findAll()).thenReturn(Flux.just(expectedCatalog));

        Flux<Catalog> catalogs = catalogService.getAll();

        StepVerifier
                .create(catalogs)
                .assertNext(catalog -> assertThat(catalog).isEqualTo(expectedCatalog))
                .verifyComplete();
    }

    @Test
    void getAll_returnsFallbackWhenRepositoryFails() {
        when(catalogRepository.findAll()).thenReturn(Flux.error(new WebClientResponseException(503, "Service Unavailable", null, null, null)));

        Flux<Catalog> catalogs = catalogService.getAll();

        StepVerifier
                .create(catalogs)
                .verifyComplete();
    }
}