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

import java.util.List;

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
        properties="stubs.find-producer=true"
)
@AutoConfigureJsonTesters
public class CatalogClientContractTests {
    @Autowired
    private CatalogClient catalogClient;

    @Test
    public void findAll() {
        List<Catalog> catalogs = catalogClient.findAll();
        assertThat(catalogs)
                .isNotNull();

        assertThat(catalogs.size()).isGreaterThan(0);
        assertThat(catalogs.get(0).getCatalogKey()).isNotNull();
        assertThat(catalogs.get(0).getDisplayName()).isNotNull();
        assertThat(catalogs.get(0).getId()).isNotNull();
    }

    @Test
    public void findByKey() {
        List<Catalog> catalogs = catalogClient.findAll();

        String catalogKey = catalogs.get(0).getCatalogKey();
        Catalog catalog = catalogClient.findByKey(catalogKey);
        assertThat(catalog.getCatalogKey()).isEqualTo(catalogKey);
        assertThat(catalog.getDisplayName()).isNotNull();
        assertThat(catalog.getId()).isNotNull();
    }
}
