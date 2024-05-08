package com.customer.entities;

import java.util.Date;

import com.customer.util.AccountTypeValid;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class AccountDetails {

	private Long accountId;

	@NotNull(message = "account balance cannot be null")
	private Long accountBalance;

	@AccountTypeValid
	private String accountType;

	private Long customerId;

	private Date accountOpeningDate;

}
