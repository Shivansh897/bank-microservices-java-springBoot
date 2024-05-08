package com.customer.payload;

import com.customer.entities.AccountDetails;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountResponse {

	@Valid
	private CustomerDto customerDto;

	@Valid
	private AccountDetails accountDetails;

}
