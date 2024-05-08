package com.customer.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountTypeValidator implements ConstraintValidator<AccountTypeValid, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value.equalsIgnoreCase("current") || value.equalsIgnoreCase("savings")
				|| value.equalsIgnoreCase("fixed_deposite")) {

			return true;
		}

		return false;
	}

}
