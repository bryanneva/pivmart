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
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.REMOTE,
        ids = "io.pivotal:pivmart-product-api",
        // NOTE: You'll need to replace the section below to point to your
        // pivmart root folder
        // repositoryRoot = "stubs://file://Users/abray/workspace/pivmart/nexus/META-INF",
        properties = "stubs.find-producer=true"
)
@AutoConfigureJsonTesters
public class CatalogClientContractTests {
    @Autowired
    private CatalogClient catalogClient;

    @Test
    public void findAll() {
        StepVerifier.create(catalogClient.findAll())
                .assertNext(result -> {
                    assertThat(result.getCatalogKey()).isNotNull();
                    assertThat(result.getDisplayName()).isNotNull();
                    assertThat(result.getId()).isNotNull();
                })
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    public void findByKey() {
        Flux<Catalog> catalogs = catalogClient.findAll();
        String catalogKey = catalogs.blockFirst().getCatalogKey();

        Catalog catalog = catalogClient.findByKey(catalogKey).block();
        assertThat(catalog.getCatalogKey()).isEqualTo(catalogKey);
        assertThat(catalog.getDisplayName()).isNotNull();
        assertThat(catalog.getId()).isNotNull();
    }
}
