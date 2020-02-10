package io.pivotal.pivmart.products;

import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Tag("integration")
class ProductsServiceProductGatewayContractTest extends ProductGatewayContractTest {

    @Autowired
    CatalogGateway catalogGateway;

    @Autowired
    ProductGateway productGateway;

    @Override
    CatalogGateway getCatalogGateway() {
        return catalogGateway;
    }

    @Override
    ProductGateway getProductGateway() {
        return productGateway;
    }
}
