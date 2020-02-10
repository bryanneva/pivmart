package io.pivotal.pivmart.products;

import java.util.List;

public interface CatalogGateway {
    List<Catalog> getAll();
}
