package io.pivotal.pivmart.services;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.repositories.CatalogRepository;
import io.pivotal.pivmart.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    private ProductService productService;
    private CatalogRepository catalogRepository;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        catalogRepository = mock(CatalogRepository.class);
        productRepository = mock(ProductRepository.class);

        productService = new ProductService(catalogRepository, productRepository);
    }

    @Test
    void getAll() {
        productService.getAll();

        verify(productRepository).findAll();
    }

    @Test
    void findAllByCatalog() {
        Catalog homeCatalog = Catalog.builder().catalogKey("home").build();
        List<Product> expected = asList(Product.builder().name("shirt").build());

        when(catalogRepository.findByKey("home")).thenReturn(homeCatalog);
        when(productRepository.findAllByCatalog(homeCatalog)).thenReturn(expected);

        List<Product> result = productService.getForCatalog("home");

        verify(productRepository).findAllByCatalog(homeCatalog);
        assertThat(result).isEqualTo(expected);
    }
}