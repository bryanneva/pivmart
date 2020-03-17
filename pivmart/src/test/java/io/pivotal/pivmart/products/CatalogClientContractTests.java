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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.REMOTE,
        ids = "io.pivotal:product-api",
        // NOTE: You'll need to replace the section below to point to your
        // pivmart root folder
        repositoryRoot = "stubs://file://Users/abray/workspace/pivmart/nexus/META-INF",
        properties = "stubs.find-producer=true"
)
@AutoConfigureJsonTesters
public class CatalogClientContractTests {
    @Autowired
    private CatalogClient catalogClient;

    @Test
    public void findAll() {
        StepVerifier
                .create(catalogClient.findAll())
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
        List<Catalog> catalogs = catalogClient.findAll().collectList().block();
        String catalogKey = catalogs.get(0).getCatalogKey();

        StepVerifier
                .create(catalogClient.findByKey(catalogKey))
                .assertNext(catalog -> {
                    assertThat(catalog.getCatalogKey()).isEqualTo(catalogKey);
                    assertThat(catalog.getDisplayName()).isNotNull();
                    assertThat(catalog.getId()).isNotNull();
                })
                .verifyComplete();
    }
}
