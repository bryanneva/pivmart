package io.pivotal.pivmart.clients;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.repositories.CatalogRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class CatalogClient implements CatalogRepository {

    private RestTemplate restTemplate;

    public CatalogClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Catalog> findAll() {
        RequestEntity<Void> requestEntity = RequestEntity
                .get(URI.create("http://localhost:8081/catalogs"))
                .build();

        return restTemplate
                .exchange(requestEntity,
                        new ParameterizedTypeReference<List<Catalog>>() {
                        })
                .getBody();
    }

    @Override
    public Catalog findByKey(String catalogKey) {
        RequestEntity<Void> requestEntity = RequestEntity
                .get(URI.create("http://localhost:8081/catalogs/" + catalogKey))
                .build();

        return restTemplate
                .exchange(requestEntity, Catalog.class)
                .getBody();
    }
}
