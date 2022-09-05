package com.sawan.repository;

import com.sawan.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomRepository extends JpaRepository<Customer,Long> {
    public Optional<Customer> findById(Long id);
}
