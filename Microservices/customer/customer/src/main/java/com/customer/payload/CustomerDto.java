package com.customer.payload;

import java.util.Date;

import com.customer.util.ValidMobileNo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

	private Long customerId;

	@NotEmpty(message = "Customer name cannot be empty !!")
	private String customerName;

	@Email(message = "Invalid email address !!")
	private String customerEmail;

	@ValidMobileNo
	private Long mobileNo;

	@NotNull(message = "Date of Birth cannot be null or empty !!")
	private Date dob;

}
