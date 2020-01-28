package io.pivotal.pivmart.products;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.REMOTE,
        ids = "io.pivotal:product-api:+:8081",
        // NOTE: You'll need to replace the section below to point to your
        // pivmart root folder
        repositoryRoot = "stubs://file://{{REPLACE_ME}}/pivmart/nexus/META-INF",
        properties="stubs.find-producer=true"
)
@AutoConfigureJsonTesters
public class ProductClientContractTest {
    @Autowired
    ProductClient productClient;

    @Test
    void findAll() {
        List<Product> products = productClient.findAll();
        assertThat(products)
                .isNotNull();

        assertThat(products.size()).isGreaterThan(0);

        assertThat(products.get(0).getId()).isNotNull();
        assertThat(products.get(0).getDescription()).isNotNull();
        assertThat(products.get(0).getName()).isNotNull();
        assertThat(products.get(0).getPrice()).isNotNull();
    }

    @Test
    void findAllByCatalogKey() {
        Catalog clothes = Catalog.builder().catalogKey("clothes").build();

        List<Product> products = productClient.findAllByCatalog(clothes);
        assertThat(products)
                .isNotNull();

        assertThat(products.size()).isGreaterThan(0);

        assertThat(products.get(0).getId()).isNotNull();
        assertThat(products.get(0).getDescription()).isNotNull();
        assertThat(products.get(0).getName()).isNotNull();
        assertThat(products.get(0).getPrice()).isNotNull();
    }
}
