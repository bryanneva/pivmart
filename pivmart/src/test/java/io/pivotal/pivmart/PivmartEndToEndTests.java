package io.pivotal.pivmart;

import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.models.Purchase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("E2E")
public class PivmartEndToEndTests {
    WebTestClient testClient;

    @BeforeEach
    void setUp() {
        testClient = WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:8765")
                .build();
    }

    @Test
    void cartCheckout_addsToPurchases() {
        List<Product> products = getProducts();

        addProductToCart(products.get(0));
        addProductToCart(products.get(1));
        checkoutCart();

        List<Purchase> purchases = getPurchases();

        assertThat(purchases).hasSizeGreaterThan(0);
    }

    private List<Purchase> getPurchases() {
        return testClient.get().uri("/api/purchases")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Purchase.class)
                .getResponseBody()
                .collectList()
                .block();
    }

    private void checkoutCart() {
        testClient.post().uri("/api/cart/checkout")
                .exchange()
                .expectStatus().isOk();
    }

    private void addProductToCart(Product product) {
        testClient.post().uri("/api/cart")
                .body(Mono.just(product), Product.class)
                .exchange()
                .expectStatus().isOk();
    }

    private List<Product> getProducts() {
        return testClient
                .get().uri("localhost:8765/api/products")
                .exchange()
                .returnResult(Product.class)
                .getResponseBody()
                .collectList().block();
    }
}
