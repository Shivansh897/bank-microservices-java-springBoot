package com.account.exceptions;

public class InsuffiecientBalanceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8998891459825087702L;

	public InsuffiecientBalanceException(String message) {
		super(message);
	}

}
