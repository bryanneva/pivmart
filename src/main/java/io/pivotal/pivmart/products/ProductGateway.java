package io.pivotal.pivmart.products;

import java.util.List;

public interface ProductGateway {
    List<Product> getForCatalog(String catalogKey);
}

