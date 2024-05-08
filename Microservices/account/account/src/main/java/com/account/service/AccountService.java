package com.account.service;

import com.account.entities.AccountDetails;
import com.account.payloads.AccountDto;

public interface AccountService {

	AccountDto createAccount(AccountDto account, Long cutomerId) throws Exception;

	void deleteAccountDetailsByCustomerId(Long customerId);

	void deleteAccountDetail(Long accountId);

	AccountDto getAccountDetailsWithCustomer(Long accountId);

	AccountDto addMoneyToAccount(Long customerId, Long accountId, Long amount) throws Exception;

	AccountDto withdrawMoneyToAccount(Long customerId, Long accountId, Long amount) throws Exception;

	AccountDto AccountToDto(AccountDetails accountDetails);

	AccountDetails DtoToAccount(AccountDto accountDto);

}
