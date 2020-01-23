package io.pivotal.pivmart.products;

import io.pivotal.pivmart.config.ProductApiProperties;
import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.repositories.CatalogRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class CatalogClient implements CatalogRepository {

    private WebClient webClient;
    private ProductApiProperties productApiProperties;

    public CatalogClient(WebClient webClient, ProductApiProperties productApiProperties) {
        this.webClient = webClient;
        this.productApiProperties = productApiProperties;
    }

    @Override
    public Mono<List<Catalog>> findAll() {
        return webClient.get().uri(productApiProperties.getUrl() + "/catalogs")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Catalog>>() {});
    }

    @Override
    public Mono<Catalog> findByKey(String catalogKey) {
        return webClient.get().uri(productApiProperties.getUrl() + "catalogs/{catalogKey}", catalogKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Catalog.class);
    }
}
