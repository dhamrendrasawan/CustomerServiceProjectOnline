package com.sawan.controller;

import com.sawan.entity.Customer;
import com.sawan.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CustomerService  customerService;

   @Test
    void  testGetCustomers() throws Exception
    {
        Customer customer1=new Customer();
        customer1.setId(100L);
        customer1.setName("Ram");

        Customer customer2=new Customer();
        customer2.setId(200L);
        customer2.setName("Ramesh");

        Mockito.when(customerService.findAll()).thenReturn(Arrays.asList(customer1,customer2));
        mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].name",is("Ram")));



    }
  @Test
    void testCustomerById() throws Exception
    {
        Customer customer=new Customer();
        customer.setName("Rama");
        customer.setId(1L);

        Mockito.when(customerService.findById(1L)).thenReturn(customer);
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
              //  .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(1)))
               // .andExpect(jsonPath("$","Rama"));
                //.andExpect(jsonPath("$",is("Rama")));
//for 404
        Mockito.when(customerService.findById(1L)).thenReturn(customer);
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/{id}",2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }


}
