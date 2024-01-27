package com.ecommerce.controller;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.request.CreateProductRequest;
import com.ecommerce.request.UpdateProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;
    private final String baseURL = "/api/product/v1";

    @AfterEach
    void tearDown(){
        productRepository.deleteAll();
    }

    @Test
    void createProductTest() throws Exception {
        CreateProductRequest createProductRequest = new CreateProductRequest(
                "Product1",
                "Product Description",
                10.0,
                100
        );
        String jsonRequestBody = objectMapper.writeValueAsString(createProductRequest);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(baseURL + "/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Product productResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Product.class);

        Assertions.assertEquals(createProductRequest.getName(), productResponse.getName());
        Assertions.assertEquals(createProductRequest.getDescription(), productResponse.getDescription());
        Assertions.assertEquals(createProductRequest.getPrice(), productResponse.getPrice());
        Assertions.assertEquals(createProductRequest.getQuantity(), productResponse.getQuantity());
    }

    @Test
    void getByIdTest() throws Exception {
        CreateProductRequest createProductRequest = new CreateProductRequest(
                "Product1",
                "Product Description",
                10.0,
                100
        );
        Product savedProduct = productRepository.save(new Product(createProductRequest));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(baseURL + "/getById/" + savedProduct.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Product productResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Product.class);

        Assertions.assertEquals(createProductRequest.getName(), productResponse.getName());
        Assertions.assertEquals(createProductRequest.getDescription(), productResponse.getDescription());
        Assertions.assertEquals(createProductRequest.getPrice(), productResponse.getPrice());
        Assertions.assertEquals(createProductRequest.getQuantity(), productResponse.getQuantity());
    }

    @Test
    void updateProductTest() throws Exception {
        CreateProductRequest createProductRequest = new CreateProductRequest(
                "Product1",
                "Product Description",
                10.0,
                100
        );
        Product savedProduct = productRepository.save(new Product(createProductRequest));

        UpdateProductRequest updateProductRequest = new UpdateProductRequest(
                savedProduct.getId(),
                "Updated Product",
                savedProduct.getDescription(),
                savedProduct.getPrice(),
                savedProduct.getQuantity()
        );
        String jsonRequestBody = objectMapper.writeValueAsString(updateProductRequest);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(baseURL + "/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Product productResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Product.class);

        Assertions.assertEquals(updateProductRequest.getName(), productResponse.getName());
    }

    @Test
    void deleteProductTest() throws Exception {
        CreateProductRequest createProductRequest = new CreateProductRequest(
                "Product1",
                "Product Description",
                10.0,
                100
        );
        Product savedProduct = productRepository.save(new Product(createProductRequest));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(baseURL + "/delete/" + savedProduct.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Assertions.assertEquals("Success", response);
    }
}
