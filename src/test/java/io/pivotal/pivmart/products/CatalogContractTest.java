package io.pivotal.pivmart.products;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class CatalogContractTest {

    abstract CatalogGateway getCatalogGateway();

    @Test
    void getAll() {
        List<Catalog> response = getCatalogGateway().getAll();
        assertThat(response.size()).isGreaterThan(0);

        assertThat(response.get(0).getCatalogKey()).isNotNull();
        assertThat(response.get(0).getDisplayName()).isNotNull();
    }
}
