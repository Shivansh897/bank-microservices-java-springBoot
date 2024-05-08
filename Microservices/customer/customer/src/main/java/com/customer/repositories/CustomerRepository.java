package com.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
