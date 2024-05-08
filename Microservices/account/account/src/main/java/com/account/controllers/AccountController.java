package com.account.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.account.payloads.AccountDto;
import com.account.payloads.View;
import com.account.service.AccountService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/account")
public class AccountController {

	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	@JsonView(View.accountDetailsOnly.class)
	@PostMapping("/{customerId}")
	public ResponseEntity<AccountDto> generateAccountWithCustomerId(@RequestBody AccountDto accountDto,
			@PathVariable Long customerId) throws Exception {
		return ResponseEntity.ok(accountService.createAccount(accountDto, customerId));
	}

	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<String> deleteAccountDetailsByCustomerId(@PathVariable Long customerId) {
		accountService.deleteAccountDetailsByCustomerId(customerId);
		return ResponseEntity.ok("All accounts are deleted");
	}

	@DeleteMapping("/{accountId}")
	public ResponseEntity<String> deleteAccountDetail(@PathVariable Long accountId) {
		accountService.deleteAccountDetail(accountId);
		return ResponseEntity.ok("Account with id" + accountId + "deleted successfully!!");
	}

	@JsonView(View.accountDetailsWithCustomerDeatils.class)
	@GetMapping("/{accountId}")
	public ResponseEntity<AccountDto> getAccountDetailsWithCustomer(@PathVariable Long accountId) {
		return ResponseEntity.ok(accountService.getAccountDetailsWithCustomer(accountId));

	}

	@JsonView(View.accountDetailsOnly.class)
	@PutMapping("/add-money/{accountId}")
	public ResponseEntity<AccountDto> addMoneyToAccount(@PathVariable Long accountId, @RequestParam Long customerId,
			@RequestParam Long amount) throws Exception {
		return ResponseEntity.ok(accountService.addMoneyToAccount(customerId, accountId, amount));

	}

	@JsonView(View.accountDetailsOnly.class)
	@PutMapping("/withdraw-money/{accountId}")
	public ResponseEntity<AccountDto> withdrawMoneyToAccount(@PathVariable Long accountId,
			@RequestParam Long customerId, @RequestParam Long amount) throws Exception {
		return ResponseEntity.ok(accountService.withdrawMoneyToAccount(customerId, accountId, amount));

	}

}
