package io.pivotal.pivmart;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.products.ProductClient;
import io.pivotal.pivmart.repositories.CatalogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureWebTestClient
public class CatalogControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CatalogRepository catalogClient;

    @Test
    void catalogs_list() throws Exception {
        Catalog expectedCatalog = Catalog.builder()
                .catalogKey("electronics")
                .displayName("Electronics")
                .build();

        when(catalogClient.findAll()).thenReturn(Flux.just(expectedCatalog));

        List<Catalog> catalogs = webTestClient.get()
                .uri("/api/catalogs")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Catalog.class)
                .getResponseBody()
                .collectList()
                .block();

        assertThat(catalogs).hasSize(1).containsExactly(expectedCatalog);
    }

}
