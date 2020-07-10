package io.pivotal.pivmart.products;

import io.pivotal.pivmart.config.WorkshopConfigProperties;
import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class ProductClient implements ProductRepository {
    private final WebClient webClient;
    private final WorkshopConfigProperties workshopConfigProperties;

    @Override
    public Flux<Product> findAll() {
        return webClient.get()
                .uri(URI.create(String.format("http://%s-product-api/", workshopConfigProperties.getPrefix())))
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Product.class);
    }

    @Override
    public Flux<Product> findAllByCatalog(Catalog catalog) {
        URI url = URI.create(String.format("http://%s-product-api/?catalog=%s", workshopConfigProperties.getPrefix(), catalog.getCatalogKey()));
        return webClient.get()
                .uri(url)
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Product.class);
    }
}
