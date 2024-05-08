package com.account.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.account.entities.AccountDetails;
import com.account.entities.Customer;
import com.account.exceptions.AccountTypeAlreadyExistException;
import com.account.exceptions.InsuffiecientBalanceException;
import com.account.exceptions.NegativeAmountException;
import com.account.exceptions.ResourceNotFoundException;
import com.account.payloads.AccountDto;
import com.account.repositories.AccountRepository;
import com.account.service.AccountService;
import com.account.service.CustomerClient;

import jakarta.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;

	private CustomerClient customerClient;

	private ModelMapper modelMapper;

	public AccountServiceImpl(AccountRepository accountRepository, CustomerClient customerClient,
			ModelMapper modelMapper) {
		super();
		this.accountRepository = accountRepository;
		this.customerClient = customerClient;
		this.modelMapper = modelMapper;
	}

	private final Random random = new Random();

	@Transactional
	@Override
	public AccountDto createAccount(AccountDto accountDto, Long customerId) throws Exception {

		AccountDetails account = this.DtoToAccount(accountDto);

		List<AccountDetails> existingAccountDetails = accountRepository.findByCustomerId(customerId);

		boolean isMatchingAccountTypeExists = existingAccountDetails.stream()
				.anyMatch(singleAccount -> singleAccount.getAccountType().equals(account.getAccountType()));

		if (isMatchingAccountTypeExists) {
			throw new AccountTypeAlreadyExistException(
					"AccountType already exists with same customer , Please choose different type of account");
		}

		long accountId = (long) (1000000000L + random.nextDouble() * 9000000000L);

		account.setAccountId(accountId);
		account.setAccountOpeningDate(new Date());
		account.setCustomerId(customerId);
		account.setAccountBalance(account.getAccountBalance());
		account.setAccountType(account.getAccountType());

		AccountDetails savedAccount = accountRepository.save(account);

		return AccountToDto(savedAccount);
	}

	@Override
	public void deleteAccountDetailsByCustomerId(Long customerId) {

		List<AccountDetails> allAccountDetails = accountRepository.findByCustomerId(customerId);

		accountRepository.deleteAll(allAccountDetails);

	}

	@Override
	public AccountDto getAccountDetailsWithCustomer(Long accountId) {
		AccountDetails accountDetails = accountRepository.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "accountId", accountId));
		Customer customerDetails = customerClient.getCustomerWithAccount(accountDetails.getCustomerId());
		AccountDto accountDto = this.AccountToDto(accountDetails);
		accountDto.setCustomer(customerDetails);
		return accountDto;

	}

	@Override
	public void deleteAccountDetail(Long accountId) {
		AccountDetails accountToBeDeleted = accountRepository.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "accountId", accountId));
		accountRepository.delete(accountToBeDeleted);

	}

	@Override
	public AccountDto addMoneyToAccount(Long customerId, Long accountId, Long amount) throws Exception {

		AccountDetails accountDetails = accountRepository.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "accountId", accountId));
		Customer currentCustomer = customerClient.getCustomerWithAccount(customerId);

		if (currentCustomer == null) {
			throw new ResourceNotFoundException("Customer ", "Customer Id", customerId);
		}

		if (currentCustomer.getCustomerId() != accountDetails.getCustomerId()) {

			throw new ResourceNotFoundException("Customer id", "AccountId", accountId);
		}

		if (amount < 0) {
			throw new NegativeAmountException("Amount cannot be negative!!");
		}

		Long updatedBalance = accountDetails.getAccountBalance() + amount;
		accountDetails.setAccountBalance(updatedBalance);

		accountDetails = accountRepository.save(accountDetails);

		return this.AccountToDto(accountDetails);

	}

	@Override
	public AccountDto withdrawMoneyToAccount(Long customerId, Long accountId, Long amount) throws Exception {

		AccountDetails accountDetails = accountRepository.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account", "accountId", accountId));
		Customer currentCustomer = customerClient.getCustomerWithAccount(customerId);

		if (currentCustomer == null) {
			throw new ResourceNotFoundException("Customer", "customerId", customerId);
		}

		if (accountDetails.getAccountBalance() < amount) {
			throw new InsuffiecientBalanceException("Insufficient Balance!!");
		}

		if (currentCustomer.getCustomerId() != accountDetails.getCustomerId()) {

			throw new ResourceNotFoundException("Customer id", "AccountId", accountId);
		}

		if (amount < 0) {
			throw new NegativeAmountException("Amount cannot be negative!!");
		}

		Long updatedBalance = accountDetails.getAccountBalance() - amount;
		accountDetails.setAccountBalance(updatedBalance);

		accountDetails = accountRepository.save(accountDetails);

		AccountDto accountDto = this.AccountToDto(accountDetails);

		return accountDto;

	}

	@Override
	public AccountDto AccountToDto(AccountDetails accountDetails) {
		AccountDto accountDto = this.modelMapper.map(accountDetails, AccountDto.class);
		return accountDto;
	}

	@Override
	public AccountDetails DtoToAccount(AccountDto accountDto) {
		AccountDetails accountDetails = this.modelMapper.map(accountDto, AccountDetails.class);
		return accountDetails;
	}

}
