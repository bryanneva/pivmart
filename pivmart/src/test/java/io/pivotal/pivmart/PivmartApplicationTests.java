package io.pivotal.pivmart;

import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@Disabled
class PivmartApplicationTests {

//    @MockBean
//    ProductClient productClient;
//
//    @MockBean
//    CatalogClient catalogClient;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    void contextLoads() {
//    }
//

//
//
//    private ResultActions addItemToCart(UUID id) throws Exception {
//        UUID catalogId = UUID.randomUUID();
//
//  language=JSON
//        String requestBody = "{\n" +
//                "  \"id\": \"" + id + "\",\n" +
//                "  \"catalogId\": \"" + catalogId + "\",\n" +
//                "  \"name\": \"name\",\n" +
//                "  \"price\": \"19.99\",\n" +
//                "  \"description\": \"description\",\n" +
//                "  \"imageSrc\": \"imageSrc\",\n" +
//                "  \"imageAlt\": \"imageAlt\"\n" +
//                "}";
//
//        MockHttpServletRequestBuilder request = post("/api/cart")
//                .contentType(APPLICATION_JSON)
//                .content(requestBody);
//
//        return mockMvc.perform(request);
//    }
//
//    private ResultActions addItemToCart() throws Exception {
//        UUID id = UUID.randomUUID();
//        return addItemToCart(id);
//    }
//
//    @Test
//    void cart_add() throws Exception {
//        ResultActions resultActions = addItemToCart();
//        resultActions.andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasKey("product")))
//        ;
//    }
//
//    @Test
//    void cart_list() throws Exception {
//        addItemToCart();
//
//        mockMvc.perform(get("/api/cart"))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)))
//                .andExpect(jsonPath("$[0]", hasKey("product")))
//        ;
//    }
//
//    @Test
//    void cart_remove() throws Exception {
//        UUID expectedUuid = UUID.randomUUID();
//        addItemToCart(expectedUuid);
//
//        mockMvc.perform(put("/api/cart/{id}", expectedUuid))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()", greaterThan(0)));
//    }
//
//    @Test
//    void cart_checkOut() throws Exception {
//        mockMvc.perform(post("/api/cart/checkout"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void purchases_list() throws Exception {
//        mockMvc.perform(get("/api/purchases"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()", greaterThan(0)));
//    }
}
