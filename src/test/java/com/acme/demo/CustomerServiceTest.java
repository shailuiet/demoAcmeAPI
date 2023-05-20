package com.acme.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.acme.demo.entity.Customer;
import com.acme.demo.repository.CustomerRepository;
import com.acme.demo.service.CustomerService;
import com.acme.demo.service.CustomerServiceImpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
  
    @Mock
    private CustomerRepository customerRepository;
  
    private CustomerService customerService;
  
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, null);
    }
  
    @Test
    public void testGetCustomerById() {
        // Define test data
    	System.out.println("running");
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("John Doe");
      
        // Mock the behavior of the customerRepository
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
      
        // Perform the test
        Customer result = customerService.getCustomerById(customerId);
      
        // Verify the result
        assertNotNull(result);
        assertEquals(customerId, result.getId());
        assertEquals("John Doe", result.getName());
      
        // Verify that the customerRepository.findById() method was called
        verify(customerRepository, times(1)).findById(customerId);
    }
  
    // Add more test methods as needed
}
