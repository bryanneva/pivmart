package io.pivotal.productapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductApiApplicationTests {
	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void catalogs_list() throws Exception {
		mockMvc.perform(get("/catalogs"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)))
				.andExpect(jsonPath("$[0]", hasKey("id")))
				.andExpect(jsonPath("$[0]", hasKey("catalogKey")))
				.andExpect(jsonPath("$[0]", hasKey("displayName")))
		;
	}
}
