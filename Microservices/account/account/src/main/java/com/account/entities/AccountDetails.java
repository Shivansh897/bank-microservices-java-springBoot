package com.account.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDetails {

	@Id
	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "account_balance")
	private Long accountBalance;

	@Column(name = "account_type")
	private String accountType;

	@Column(name = "customer_id")
	private Long customerId;

	@CreationTimestamp
	@DateTimeFormat
	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_opening")
	private Date accountOpeningDate;



}
