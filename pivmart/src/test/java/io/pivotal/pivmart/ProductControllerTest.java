package io.pivotal.pivmart;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.repositories.CatalogRepository;
import io.pivotal.pivmart.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureWebTestClient
public class ProductControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductRepository productClient;

    @MockBean
    private CatalogRepository catalogClient;

    @Test
    void products_list() throws Exception {
        Product expectedProduct = Product.builder()
                .name("anything")
                .catalogId(UUID.randomUUID())
                .build();

        Catalog electronicsCatalog = Catalog.builder()
                .catalogKey("electronics")
                .build();

        when(catalogClient.findByKey("electronics")).thenReturn(Mono.just(electronicsCatalog));
        when(productClient.findAllByCatalog(electronicsCatalog)).thenReturn(Flux.just(expectedProduct));


        List<Product> products = webTestClient.get()
                .uri("/api/products?catalog=electronics")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Product.class)
                .getResponseBody()
                .collectList()
                .block();

        assertThat(products).hasSize(1).containsExactly(expectedProduct);
    }
}
