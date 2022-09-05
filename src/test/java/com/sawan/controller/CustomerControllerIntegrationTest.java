package com.sawan.controller;

import com.sawan.entity.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate  testRestTemplate;

    @Test
    void testGetCustomersIntegration()
    {
        List<Customer> customers=testRestTemplate.getForObject("http://localhost:"+port+"/customers",List.class);
        Assertions.assertNotNull(customers);
        Assertions.assertEquals(2,customers.size());
    }
    @Test
    void testGetCustomerIntegrationById()
    {
       Customer customer= testRestTemplate.getForObject("http://localhost:"+port+"/customer/{id}",Customer.class,1L);
        Assertions.assertNotNull(customer);
        Assertions.assertEquals("shyam",customer.name);

//404
        Customer customer1= testRestTemplate.getForObject("http://localhost:"+port+"/customer/{id}",Customer.class,7L);
        Assertions.assertNull(customer1);

    }
}
