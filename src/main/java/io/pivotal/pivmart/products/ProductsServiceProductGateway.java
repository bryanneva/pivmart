package io.pivotal.pivmart.products;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ProductsServiceProductGateway implements ProductGateway {
    private RestTemplate restTemplate;

    public ProductsServiceProductGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getForCatalog(String catalogKey) {
        Product[] products = restTemplate.getForObject("http://localhost:8081/api/products?catalog=" + catalogKey, Product[].class);
        return Arrays.asList(products);
    }
}
