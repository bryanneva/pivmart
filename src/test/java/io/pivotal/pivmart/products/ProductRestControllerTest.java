package io.pivotal.pivmart.products;

import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductRestController.class)
class ProductRestControllerTest {

    @MockBean
    ProductGateway productGateway;

    @Autowired
    MockMvc mockMvc;

    @Captor
    ArgumentCaptor<String> getForCatalogCaptor;

    @Test
    void list_returnsProductsForCatalog() throws Exception {
        String catalogName = RandomString.make();

        when(productGateway.getForCatalog(catalogName))
                .thenReturn(asList(Product.builder().build()));

        mockMvc.perform(get("/api/products?catalog={catalog}", catalogName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)))
                .andExpect(jsonPath("$[0]", hasKey("name")))
                .andExpect(jsonPath("$[0]", hasKey("catalogId")))
        ;

        verify(productGateway).getForCatalog(getForCatalogCaptor.capture());

        assertThat(getForCatalogCaptor.getValue()).isEqualTo(catalogName);
    }
}
