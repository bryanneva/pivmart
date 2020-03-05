package io.pivotal.pivmart;

import io.pivotal.pivmart.products.CatalogClient;
import io.pivotal.pivmart.products.ProductClient;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Disabled
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

//    @Test
//    void products_list() throws Exception {
//        List<Product> products = asList(Product.builder().build());
//        when(productClient.findAllByCatalog(any())).thenReturn(products);
//
//        mockMvc.perform(get("/api/products?catalog={catalog}", "electronics"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()", greaterThan(0)))
//                .andExpect(jsonPath("$[0]", hasKey("name")))
//                .andExpect(jsonPath("$[0]", hasKey("catalogId")))
//                .andDo(print())
//        ;
//    }

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
