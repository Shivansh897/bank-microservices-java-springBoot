package com.account.exceptions;

public class InvalidCustomerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3992456163277601832L;

	public InvalidCustomerException(String message) {
		super(message);
	}
}
