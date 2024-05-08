package com.account.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.account.entities.AccountDetails;

public interface AccountRepository extends JpaRepository<AccountDetails, Long> {

	List<AccountDetails> findByCustomerId(Long customerId);

}
