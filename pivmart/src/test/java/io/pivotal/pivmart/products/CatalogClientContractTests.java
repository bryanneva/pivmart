package io.pivotal.pivmart.products;

import io.pivotal.pivmart.models.Catalog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogClientContractTests extends AbstractFakeNexusSetup {

    @Autowired
    private CatalogClient subject;

    @Test
    public void testCatalogClient() {
//        fail("Add test to verify consumption of catalog contract");

        Mono<List<Catalog>> actual = subject.findAll();
        assertThat(actual.block())
                .isNotNull()
                .hasSize(4);

    }

}
