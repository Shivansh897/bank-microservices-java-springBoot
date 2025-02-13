package com.customer.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AccountTypeValidator.class)
public @interface AccountTypeValid {

	// error message
	String message() default "Invalid Account Type";

	// represents group of constraints
	Class<?>[] groups() default {};

	// additional info about annotation
	Class<? extends Payload>[] payload() default {};

}
