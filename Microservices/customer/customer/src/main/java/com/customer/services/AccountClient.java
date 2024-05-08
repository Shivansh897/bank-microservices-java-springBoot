package com.customer.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.customer.entities.AccountDetails;

@FeignClient(name = "ACCOUNT-SERVICE")
public interface AccountClient {

	@DeleteMapping("/account/customer/{customerId}")
	void deleteAccountsOfCustomer(@PathVariable Long customerId);

	@PostMapping("/account/{customerId}")
	AccountDetails generateAccount(@RequestBody AccountDetails accountDetails, @PathVariable Long customerId);

}
