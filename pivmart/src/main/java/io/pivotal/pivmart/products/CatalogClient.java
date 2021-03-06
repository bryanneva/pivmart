package io.pivotal.pivmart.products;

import io.pivotal.pivmart.config.ProductApiProperties;
import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.repositories.CatalogRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class CatalogClient implements CatalogRepository {

    private RestTemplate restTemplate;
    private ProductApiProperties productApiProperties;

    public CatalogClient(RestTemplate restTemplate, ProductApiProperties productApiProperties) {
        this.restTemplate = restTemplate;
        this.productApiProperties = productApiProperties;
    }

    @Override
    public List<Catalog> findAll() {
        RequestEntity<Void> request = RequestEntity
                .get(URI.create(productApiProperties.getUrl() + "/catalogs"))
                .accept(MediaType.APPLICATION_JSON)
                .build();

        ResponseEntity<List<Catalog>> response = restTemplate.exchange(
                request,
                new ParameterizedTypeReference<List<Catalog>>() {
                }
        );

        return response.getBody();
    }

    @Override
    public Catalog findByKey(String catalogKey) {
        ResponseEntity<Catalog> response = restTemplate.exchange(
                URI.create(productApiProperties.getUrl() + "catalogs/" + catalogKey),
                HttpMethod.GET,
                null,
                Catalog.class
        );

        return response.getBody();
    }
}
