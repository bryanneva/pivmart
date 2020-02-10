package io.pivotal.pivmart.config;

import io.pivotal.pivmart.products.Catalog;
import io.pivotal.pivmart.products.CatalogGateway;
import io.pivotal.pivmart.products.Product;
import io.pivotal.pivmart.products.ProductGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

import static java.util.Arrays.asList;

@Configuration
@Profile("local")
public class FakeProductsServiceConfig {

    private static class FakeCatalogGateway implements CatalogGateway {
        @Override
        public List<Catalog> getAll() {
            return asList(Catalog.builder()
                    .catalogKey("A")
                    .displayName("A")
                    .build());
        }
    }

    private static class FakeProductGateway implements ProductGateway {
        @Override
        public List<Product> getForCatalog(String catalogKey) {
            return asList(Product.builder()
                    .name("Some Product")
                    .price("1.00")
                    .build());
        }
    }

    @Bean
    public CatalogGateway catalogGateway() {
        return new FakeCatalogGateway();
    }

    @Bean
    public ProductGateway productGateway() {
        return new FakeProductGateway();
    }
}
