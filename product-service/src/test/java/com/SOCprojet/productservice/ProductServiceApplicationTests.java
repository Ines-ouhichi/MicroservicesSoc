package com.SOCprojet.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class ProductServiceApplicationTests {

	/* @Autowired
	 private MockMvc mockMvc;
	 @Autowired
	 private ObjectMapper objectMapper;
	 @Autowired
	 private ProductRepository productRepository;
	
	 static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8");
	 
	 @DynamicPropertySource
	    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
	        registry.add("spring.datasource.url", mysql::getJdbcUrl);
	        registry.add("spring.datasource.username", mysql::getUsername);
	        registry.add("spring.datasource.password", mysql::getPassword);
	    }
	
	 @Test
	    void shouldCreateProduct() throws Exception {
	        ProductRequest productRequest = getProductRequest();
	        String productRequestString = objectMapper.writeValueAsString(productRequest);
	        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(productRequestString))
	                .andExpect(status().isCreated());
	        Assertions.assertEquals(1, productRepository.findAll().size());
	    }
	 
	 private ProductRequest getProductRequest() {
	        return ProductRequest.builder()
	                .name("iPhone 13")
	                .description("iPhone 13")
	                .price(BigDecimal.valueOf(1200))
	                .build();
	    }*/
	 @Test
		void contextLoads() {
		}

}
