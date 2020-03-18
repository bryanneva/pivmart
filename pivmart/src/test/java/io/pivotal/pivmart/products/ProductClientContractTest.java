package io.pivotal.pivmart.products;

import io.pivotal.pivmart.models.Catalog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.REMOTE,
        ids = "io.pivotal:product-api",
        // NOTE: You'll need to replace the section below to point to your
        // pivmart root folder
        repositoryRoot = "stubs://file://Users/abray/workspace/pivmart/nexus/META-INF",
        properties = "stubs.find-producer=true",
        mappingsOutputFolder = "build/mapping"
)
@AutoConfigureJsonTesters
public class ProductClientContractTest {
    @Autowired
    ProductClient productClient;

    @Test
    void findAll() {
        StepVerifier
                .create(productClient.findAll())
                .assertNext(product -> {
                    assertThat(product.getId()).isNotNull();
                    assertThat(product.getDescription()).isNotNull();
                    assertThat(product.getName()).isNotNull();
                    assertThat(product.getPrice()).isNotNull();
                })
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findAllByCatalogKey() {
        Catalog clothes = Catalog.builder().catalogKey("clothes").build();

        StepVerifier
                .create(productClient.findAllByCatalog(clothes))
                .assertNext(product -> {
                    assertThat(product.getId()).isNotNull();
                    assertThat(product.getDescription()).isNotNull();
                    assertThat(product.getName()).isNotNull();
                    assertThat(product.getPrice()).isNotNull();
                })
                .verifyComplete();
    }
}
