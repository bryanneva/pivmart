package io.pivotal.pivmart.products;

import io.pivotal.pivmart.config.ProductsServiceProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Arrays.asList;

public class ProductsServiceCatalogGateway implements CatalogGateway {

    private RestTemplate restTemplate;
    private ProductsServiceProperties productsServiceProperties;

    @Value("${productsservice.url}")
    String url;

    public ProductsServiceCatalogGateway(RestTemplate restTemplate, ProductsServiceProperties productsServiceProperties) {
        this.restTemplate = restTemplate;
        this.productsServiceProperties = productsServiceProperties;
    }

    @Override
    public List<Catalog> getAll() {
        Catalog[] response = restTemplate.getForObject(url + "/api/catalogs", Catalog[].class);
        return asList(response);
    }
}
