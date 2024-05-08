package com.account.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.account.entities.Customer;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerClient {

	@GetMapping("/customer/{customerId}")
	Customer getCustomerWithAccount(@PathVariable Long customerId);

}
