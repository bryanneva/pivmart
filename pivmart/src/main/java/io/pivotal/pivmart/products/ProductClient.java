package io.pivotal.pivmart.products;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class ProductClient implements ProductRepository {
    private final WebClient webClient;

    @Override
    public Flux<Product> findAll() {
        return webClient.get()
                .uri(URI.create("http://product-api/"))
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Product.class);
    }

    @Override
    public Flux<Product> findAllByCatalog(Catalog catalog) {
        URI url = URI.create("http://product-api/?catalog=" + catalog.getCatalogKey());

        return webClient.get()
                .uri(url)
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Product.class);
    }
}
