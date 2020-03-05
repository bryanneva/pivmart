package io.pivotal.pivmart.services;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.repositories.CatalogRepository;
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
    CatalogRepository catalogClient;

    @Test
    void getAll() {
        when(catalogClient.findAll()).thenReturn(Flux.just(Catalog.builder().catalogKey("groceries").build()));

        StepVerifier.create(catalogService.getAll())
                .assertNext(catalog -> assertThat(catalog.getCatalogKey()).isEqualTo("groceries"))
                .verifyComplete();
    }

    @Test
    void getAll_returnsFallbackWhenRepositoryFails() {
        when(catalogClient.findAll()).thenReturn(Flux.error(new WebClientResponseException(503, "Service Unavailable", null, null, null)));

        StepVerifier.create(catalogService.getAll())
                .verifyComplete();
    }
}