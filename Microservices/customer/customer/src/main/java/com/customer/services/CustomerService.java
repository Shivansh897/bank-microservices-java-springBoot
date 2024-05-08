package com.customer.services;

import java.util.List;

import com.customer.entities.Customer;
import com.customer.payload.CustomerAccountResponse;
import com.customer.payload.CustomerDto;

public interface CustomerService {

	/**
	 * add- This method is responsible for a the customer with their respective
	 * account
	 * 
	 * @paramcustomerAccountResponse
	 * @return customerAccountResponse
	 * @author shivanshsingh01
	 */
	CustomerAccountResponse add(CustomerAccountResponse customerAccountResponse);

	/**
	 * getAllCustomer- This method is responsible for fetching List of all customers
	 * 
	 * @return all customers
	 * @author shivanshsingh01
	 */
	List<CustomerDto> getAllCustomers();

	/**
	 * getCustomer- This method is responsible for fetching single customer
	 * 
	 * @param id
	 * @return single customer
	 * @author shivanshsingh01
	 */

	CustomerDto getCustomer(Long id);

	/**
	 * updateCustomer- This method is responsible for updating customer information
	 * 
	 * @param id
	 * @param customer
	 * @return CustomerDto
	 * @author shivanshsingh01
	 */
	CustomerDto updateCustomer(Long id, CustomerDto customer);

	/**
	 * deleteCustomer - This method is responsible for deleting customer
	 * 
	 * @param id
	 * @author shivanshsingh01
	 */
	void deleteCustomer(Long id);

	/**
	 * dtoToCustomer - This method is responsible for mapping CustomerDto to
	 * Customer
	 * 
	 * @param customerDto
	 * @return Customer
	 * @author shivanshsingh01
	 */
	Customer dtoToCustomer(CustomerDto customerDto);

	/**
	 * customerToDto- This method is responsible for mapping Customer to CustomerDto
	 * 
	 * @param customer
	 * @return CustomerDto
	 * @author shivanshsingh01
	 */
	CustomerDto customerToDto(Customer customer);

}
