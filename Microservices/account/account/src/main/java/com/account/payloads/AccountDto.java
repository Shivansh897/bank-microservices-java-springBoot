package com.account.payloads;

import java.util.Date;

import com.account.entities.Customer;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

	@JsonView(View.accountDetailsOnly.class)
	private Long accountId;

	@JsonView(View.accountDetailsOnly.class)

	private Long accountBalance;

	@JsonView(View.accountDetailsOnly.class)
	private String accountType;

	@JsonView(View.accountDetailsOnly.class)
	private Long customerId;

	@JsonView(View.accountDetailsOnly.class)
	private Date accountOpeningDate;

	@JsonView(View.accountDetailsWithCustomerDeatils.class)
	private Customer customer;

}
