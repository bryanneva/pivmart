package io.pivotal.productapi.contracts;

import io.pivotal.productapi.*;
import io.pivotal.productapi.database.ProductFactory;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public abstract class ProductBase {
    @MockBean
    private CatalogRepository mockCatalogRepository;
    @MockBean
    private ProductRepository mockProductRepository;

    @Autowired
    private ProductEndpoint controller;

    @BeforeEach
    void setUp() {
        Catalog clothes = Catalog.builder()
                .id(UUID.fromString("f94ac11b-a492-4706-aa66-7f7fd47c23a4"))
                .catalogKey("clothes")
                .displayName("Clothes")
                .build();

        Catalog electronics = Catalog.builder()
                .id(UUID.fromString("c882752b-6e81-4a13-bca5-cdb3fa04cecc"))
                .catalogKey("electronics")
                .displayName("Electronics")
                .build();

        Product nintendoSwitch = Product.builder()
                .id(UUID.fromString("940459d7-c354-414f-b41b-91f029d59722"))
                .catalogId(electronics.getId())
                .name("Nintendo Switch")
                .price("90.99")
                .description("This is a description of Nintendo Switch")
                .imageSrc("https://picsum.photos/id/8/200/300")
                .imageAlt("Product Alt Text")
                .build();

        Product tShirt = Product.builder()
                .id(UUID.fromString("9d48b03d-94d3-4ec9-b3b4-2c40dbbe98c1"))
                .catalogId(clothes.getId())
                .name("T-Shirt")
                .price("20.97")
                .description("This is a description of T-Shirt")
                .imageSrc("https://picsum.photos/id/7/200/300")
                .imageAlt("Product Alt Text")
                .build();

        List<Product> fakeProducts = new ArrayList<>();
        fakeProducts.add(nintendoSwitch);
        fakeProducts.add(tShirt);

        when(mockCatalogRepository.findByKey("clothes")).thenReturn(clothes);
        when(mockProductRepository.findAll()).thenReturn(fakeProducts);
        when(mockProductRepository.findAllByCatalog(clothes)).thenReturn(Collections.singletonList(tShirt));

        mockMvc(standaloneSetup(controller).build());
    }

}
