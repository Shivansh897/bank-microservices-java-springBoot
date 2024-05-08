package com.customer.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.customer.entities.AccountDetails;
import com.customer.entities.Customer;
import com.customer.exceptions.ResourceNotFoundException;
import com.customer.payload.CustomerAccountResponse;
import com.customer.payload.CustomerDto;
import com.customer.repositories.CustomerRepository;
import com.customer.services.AccountClient;
import com.customer.services.CustomerService;

import jakarta.transaction.Transactional;

@Service

public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	private ModelMapper modelMapper;

	private AccountClient accountClient;

	public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper,
			AccountClient accountClient) {
		super();
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
		this.accountClient = accountClient;
	}

	@Override
	@Transactional
	public CustomerAccountResponse add(CustomerAccountResponse customerAccountResponse) {

		Customer customer = new Customer();
		customer.setCustomerEmail(customerAccountResponse.getCustomerDto().getCustomerEmail());
		customer.setCustomerName(customerAccountResponse.getCustomerDto().getCustomerName());
		customer.setDob(customerAccountResponse.getCustomerDto().getDob());
		customer.setMobileNo(customerAccountResponse.getCustomerDto().getMobileNo());
		Customer customer1 = this.customerRepository.save(customer);
		CustomerDto customerDto = this.customerToDto(customer1);

		AccountDetails accountDetails = accountClient.generateAccount(customerAccountResponse.getAccountDetails(),
				customer1.getCustomerId());

		CustomerAccountResponse finalCustomerAccountResponse = new CustomerAccountResponse();
		finalCustomerAccountResponse.setAccountDetails(accountDetails);
		finalCustomerAccountResponse.setCustomerDto(customerDto);

		return finalCustomerAccountResponse;
	}

	@Override
	public List<CustomerDto> getAllCustomers() {

		List<Customer> allCustomers = this.customerRepository.findAll();

		List<CustomerDto> customerDtos = allCustomers.stream().map(customer -> this.customerToDto(customer))
				.collect(Collectors.toList());
		return customerDtos;

	}

	@Override
	public CustomerDto getCustomer(Long id) {

		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer ", "customerId", id));
		return this.customerToDto(customer);
	}

	@Override
	public CustomerDto updateCustomer(Long id, CustomerDto customer) {
		Customer customerToBeUpdated = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer ", "customerId", id));
		customerToBeUpdated.setCustomerEmail(customer.getCustomerEmail());
		customerToBeUpdated.setCustomerName(customer.getCustomerName());
		customerToBeUpdated.setDob(customer.getDob());
		customerToBeUpdated.setMobileNo(customer.getMobileNo());
		this.customerRepository.save(customerToBeUpdated);
		return customerToDto(customerToBeUpdated);
	}

	@Override
	public void deleteCustomer(Long id) {

		Customer customerToBeDeleted = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer ", "customerId", id));

		customerRepository.delete(customerToBeDeleted);
		this.customerToDto(customerToBeDeleted);
	}

	@Override
	public Customer dtoToCustomer(CustomerDto customerDto) {
		Customer customer = this.modelMapper.map(customerDto, Customer.class);
		return customer;
	}

	@Override
	public CustomerDto customerToDto(Customer customer) {
		CustomerDto customerDto = this.modelMapper.map(customer, CustomerDto.class);
		return customerDto;
	}

}
