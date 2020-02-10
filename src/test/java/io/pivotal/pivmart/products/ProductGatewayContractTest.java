package io.pivotal.pivmart.products;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class ProductGatewayContractTest {

    abstract CatalogGateway getCatalogGateway();

    abstract ProductGateway getProductGateway();

    @Test
    void getForCatalog() {
        List<Catalog> catalogs = getCatalogGateway().getAll();

        assertThat(catalogs.size()).isGreaterThan(0);

        String catalogKey = catalogs.get(0).getCatalogKey();
        List<Product> products = getProductGateway().getForCatalog(catalogKey);

        assertThat(products.size()).isGreaterThan(0);
    }
}
