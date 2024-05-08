package com.customer.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.payload.CustomerAccountResponse;
import com.customer.payload.CustomerDto;
import com.customer.services.AccountClient;
import com.customer.services.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private CustomerService customerService;

	private AccountClient accountClient;

	public CustomerController(CustomerService customerService, AccountClient accountClient) {
		super();
		this.customerService = customerService;

		this.accountClient = accountClient;
	}

	@PostMapping
	public ResponseEntity<CustomerAccountResponse> addCustomer(
			@RequestBody @Valid CustomerAccountResponse customerAccountDetails) {
		try {
			CustomerAccountResponse addedCustomer = customerService.add(customerAccountDetails);
			return ResponseEntity.ok(addedCustomer);
		} catch (Exception e) {
			// Log the exception
			e.printStackTrace();
			return ResponseEntity.status(500).build(); // Internal Server Error
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<CustomerDto>> getAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getSingleUser(@PathVariable Long customerId) {
		return ResponseEntity.ok(this.customerService.getCustomer(customerId));
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<CustomerDto> updateCustomer(@RequestBody @Valid CustomerDto customer,
			@PathVariable Long customerId) {

		CustomerDto updatedCustomer = customerService.updateCustomer(customerId, customer);

		return ResponseEntity.ok(updatedCustomer);
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
		customerService.deleteCustomer(customerId);
		accountClient.deleteAccountsOfCustomer(customerId);
		return ResponseEntity.ok("Customers and accounts associated are deleted with id" + customerId);
	}

}
