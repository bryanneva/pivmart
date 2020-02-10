package io.pivotal.pivmart.products;

import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Tag("integration")
public class ProductsServiceCatalogContractTest extends CatalogContractTest {
    @Autowired
    CatalogGateway catalogGateway;

    @Override
    CatalogGateway getCatalogGateway() {
        return catalogGateway;
    }
}
