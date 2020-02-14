package io.pivotal.pivmart.products;

import io.pivotal.pivmart.config.ProductApiProperties;
import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class ProductClient implements ProductRepository {
    private final WebClient webClient;

    @Override
    public List<Product> findAll() {
        ParameterizedTypeReference<List<Product>> expectedType = new ParameterizedTypeReference<List<Product>>() {
        };

        return webClient.get()
                .uri(URI.create("http://product-api/"))
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(expectedType)
                .block();
    }

    @Override
    public List<Product> findAllByCatalog(Catalog catalog) {
        URI url = URI.create("http://product-api/?catalog=" + catalog.getCatalogKey());
        ParameterizedTypeReference<List<Product>> expectedType = new ParameterizedTypeReference<List<Product>>() {
        };

        return webClient.get()
                .uri(url)
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(expectedType)
                .block();
    }
}
