package io.pivotal.pivmart.products;

import io.pivotal.pivmart.config.ProductApiProperties;
import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.repositories.ProductRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;

@Component
public class ProductClient implements ProductRepository {
    private WebClient webClient;
    private ProductApiProperties productApiProperties;

    public ProductClient(RestTemplate restTemplate, ProductApiProperties productApiProperties) {
        this.productApiProperties = productApiProperties;
        this.webClient = WebClient.builder().baseUrl(productApiProperties.getUrl()).build();
    }

    @Override
    public List<Product> findAll() {
        return webClient.get()
                .uri("")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {
                })
                .block();
    }

    @Override
    public List<Product> findAllByCatalog(Catalog catalog) {
        URI url = URI.create(productApiProperties.getUrl() + "?catalog=" + catalog.getCatalogKey());

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {
                })
                .block();
    }
}
