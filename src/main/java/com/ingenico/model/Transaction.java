package com.ingenico.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="transaction_seq",initialValue=1,allocationSize=100)
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="transaction_seq")
	private Long id;
	// forign key of account id
	private Date dateOfTransaction;
	private double amountOfTransaction;
	
	@ManyToOne()
	@JoinColumn(name="account_id")
	private Account account; 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}
	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	public double getAmountOfTransaction() {
		return amountOfTransaction;
	}
	public void setAmountOfTransaction(double amountOfTransaction) {
		this.amountOfTransaction = amountOfTransaction;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
