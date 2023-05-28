package com.mamunrs.servicecore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mamunrs.servicecore.product.ProductController;
import com.mamunrs.servicecore.product.entity.Product;
import com.mamunrs.servicecore.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.StringContains.containsString;


@WebMvcTest(ProductController.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;


    @Test
    public void givenProducts_whenGetAllProducts_thenReturnJsonArray() throws Exception {

        Product product = new Product();
        product.setId(1L);
        product.setCode("IT0001");
        product.setName("Laptop");
        List<Product> allProducts = Arrays.asList(product);

        // service mock
        when(productService.getAllProduct()).thenReturn(allProducts);

        mockMvc.perform(get("/api/v1/product/all")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Hello, Mock")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").isNotEmpty())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(product.getName())));
    }


//    @Test
//    public void greetingShouldReturnMessageFromService() throws Exception {
//        when(service.greet()).thenReturn("Hello, Mock");
//        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("Hello, Mock")));
//    }

//    @Test
//    void shouldReturnStockPriceFromService() throws Exception {
//        when(stockService.getLatestPrice("AMZN"))
//                .thenReturn(BigDecimal.TEN);
//
//        this.mockMvc
//                .perform(get("/api/stocks?stockCode=AMZN"))
//                .andExpect(status().isOk());
//    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
