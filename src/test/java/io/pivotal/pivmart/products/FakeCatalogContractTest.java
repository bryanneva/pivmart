package io.pivotal.pivmart.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
public class FakeCatalogContractTest extends CatalogContractTest {

    @Autowired
    CatalogGateway catalogGateway;

    @Override
    CatalogGateway getCatalogGateway() {
        return catalogGateway;
    }
}
