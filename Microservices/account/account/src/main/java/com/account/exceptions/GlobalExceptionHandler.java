package com.account.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.account.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex) {

		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		Map<String, String> response = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> response.put(error.getField(), error.getDefaultMessage()));

		return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NegativeAmountException.class)
	public ResponseEntity<ApiResponse> handleNegativeAmountException(NegativeAmountException ex) {

		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidCustomerException.class)
	public ResponseEntity<ApiResponse> handleInvalidCustomerException(Exception ex) {

		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(InsuffiecientBalanceException.class)
	public ResponseEntity<ApiResponse> handleInsuffiecientBalanceException(Exception ex) {

		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		String message = "Parameter '" + ex.getName() + "' must be of type '" + ex.getRequiredType().getSimpleName()
				+ "'";
		return new ApiResponse(message, false);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiResponse handleMissingParameterException(MissingServletRequestParameterException ex) {
		String message = "Required parameter '" + ex.getParameterName() + "' is missing";
		return new ApiResponse(message, false);
	}

	@ExceptionHandler(AccountTypeAlreadyExistException.class)
	public ResponseEntity<ApiResponse> handleAccountTypeAlreadyExistException(AccountTypeAlreadyExistException ex) {

		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

}
