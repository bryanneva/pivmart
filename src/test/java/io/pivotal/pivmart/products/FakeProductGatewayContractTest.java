package io.pivotal.pivmart.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
public class FakeProductGatewayContractTest extends ProductGatewayContractTest {
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
