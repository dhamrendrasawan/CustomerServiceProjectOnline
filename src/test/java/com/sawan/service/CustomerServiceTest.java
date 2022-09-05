package com.sawan.service;

import com.sawan.entity.Customer;
import com.sawan.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceTest {
    @Autowired
    CustomRepository customRepository;

    public List<Customer> findAll()
    {
        return customRepository.findAll();
    }
    public Customer findById(Long id)
    {
       // Customer customer=null;
        Optional<Customer> customerOptional=customRepository.findById(id);
        if(customerOptional.isPresent())
        {
            Customer   customer=customerOptional.get();
            return customer;
        }
        return null;
    }

}
