package com.customer.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MobileNoValidator implements ConstraintValidator<ValidMobileNo, Long> {

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {

		String mobileNo = String.valueOf(value);

		if (value != null && mobileNo.length() == 10) {

			return true;
		}

		return false;
	}

}
