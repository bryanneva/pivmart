package io.pivotal.pivmart;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.models.Product;
import io.pivotal.pivmart.products.CatalogClient;
import io.pivotal.pivmart.products.ProductClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PivmartApplicationTests {

    @MockBean
    ProductClient productClient;

    @MockBean
    CatalogClient catalogClient;

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void products_list() throws Exception {
        List<Product> products = asList(Product.builder().build());
        when(productClient.findAllByCatalog(any())).thenReturn(products);

        mockMvc.perform(get("/api/products?catalog={catalog}", "electronics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)))
                .andExpect(jsonPath("$[0]", hasKey("name")))
                .andExpect(jsonPath("$[0]", hasKey("catalogId")))
                .andDo(print())
        ;
    }

    @Test
    void catalogs_list() throws Exception {
        List<Catalog> catalogs = asList(Catalog.builder().build());
        when(catalogClient.findAll()).thenReturn(catalogs);

        mockMvc.perform(get("/api/catalogs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$[0]", hasKey("id")))
                .andExpect(jsonPath("$[0]", hasKey("catalogKey")))
                .andExpect(jsonPath("$[0]", hasKey("displayName")))
        ;
    }

    private ResultActions addItemToCart(UUID id) throws Exception {
        UUID catalogId = UUID.randomUUID();

        //language=JSON
        String requestBody = "{\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"catalogId\": \"" + catalogId + "\",\n" +
                "  \"name\": \"name\",\n" +
                "  \"price\": \"19.99\",\n" +
                "  \"description\": \"description\",\n" +
                "  \"imageSrc\": \"imageSrc\",\n" +
                "  \"imageAlt\": \"imageAlt\"\n" +
                "}";

        MockHttpServletRequestBuilder request = post("/api/cart")
                .contentType(APPLICATION_JSON)
                .content(requestBody);

        return mockMvc.perform(request);
    }

    private ResultActions addItemToCart() throws Exception {
        UUID id = UUID.randomUUID();
        return addItemToCart(id);
    }

    @Test
    void cart_add() throws Exception {
        ResultActions resultActions = addItemToCart();
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasKey("product")))
        ;
    }

    @Test
    void cart_list() throws Exception {
        addItemToCart();

        mockMvc.perform(get("/api/cart"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$[0]", hasKey("product")))
        ;
    }

    @Test
    void cart_remove() throws Exception {
        UUID expectedUuid = UUID.randomUUID();
        addItemToCart(expectedUuid);

        mockMvc.perform(put("/api/cart/{id}", expectedUuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @Test
    void cart_checkOut() throws Exception {
        mockMvc.perform(post("/api/cart/checkout"))
                .andExpect(status().isOk());
    }

    @Test
    void purchases_list() throws Exception {
        mockMvc.perform(get("/api/purchases"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }
}
