package com.account.entities;

import java.util.Date;

import com.account.payloads.View;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

	@JsonView(View.accountDetailsOnly.class)
	private Long customerId;

	@JsonView(View.accountDetailsOnly.class)
	private String customerName;

	@JsonView(View.accountDetailsOnly.class)
	private String customerEmail;

	@JsonView(View.accountDetailsOnly.class)
	private Long mobileNo;

	@JsonView(View.accountDetailsOnly.class)
	@Column(name = "dob")
	@Temporal(TemporalType.DATE)
	private Date dob;

}
