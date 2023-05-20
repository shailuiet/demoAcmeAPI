package com.acme.demo;
import com.acme.demo.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = DemoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerControllerIntegrationTest {

	  @LocalServerPort
	  private int port;
	  @Autowired
	  private TestRestTemplate restTemplate;
    @Test
    public void testAddEmployee() {
    	System.out.println("inside run");
      Customer customer = new Customer();
    ResponseEntity<String> responseEntity = this.restTemplate
    .getForEntity("http://localhost:" + port + "/demoApp/hello",String.class);
//      ResponseEntity<String> responseEntity = this.restTemplate
//        .postForEntity("http://localhost:" + port + "/demoApp/hello", customer, String.class);
    System.out.println(responseEntity.getBody());  
    assertEquals(200, responseEntity.getStatusCodeValue());
      assertEquals(responseEntity.getBody(), "Hi");
    }
//    public void testGetCustomerById() throws Exception {
//        // Create a customer first
//        Customer customer = new Customer();
//        customer.setId(1L);
//        customer.setName("John Doe");
//
//        // Convert customer object to JSON
//        String customerJson = objectMapper.writeValueAsString(customer);
//
//        // Perform a POST request to create the customer
//        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(customerJson))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        // Extract the created customer from the response
//        String responseContent = createResult.getResponse().getContentAsString();
//        Customer createdCustomer = objectMapper.readValue(responseContent, Customer.class);
//
//        // Perform a GET request to retrieve the customer by ID
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{id}", createdCustomer.getId()))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(createdCustomer.getId()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(createdCustomer.getName()));
//    }

    // Add more integration test methods as needed
}
