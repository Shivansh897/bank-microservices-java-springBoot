package com.account.exceptions;

public class AccountTypeAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountTypeAlreadyExistException(String message) {
		super(message);
	}
}
