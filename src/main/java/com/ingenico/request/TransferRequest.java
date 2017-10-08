package com.ingenico.request;

public class TransferRequest {
	private Long fromCustomerId;
	private String fromAccountNumber;
	private Long toCustomerId;
	private String toAccountNumber;
	private double amount;
	private String fromAccountLastUpdateDate;
	
	public Long getFromCustomerId() {
		return fromCustomerId;
	}
	public void setFromCustomerId(Long fromCustomerId) {
		this.fromCustomerId = fromCustomerId;
	}
	public String getFromAccountNumber() {
		return fromAccountNumber;
	}
	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}
	public Long getToCustomerId() {
		return toCustomerId;
	}
	public void setToCustomerId(Long toCustomerId) {
		this.toCustomerId = toCustomerId;
	}
	public String getToAccountNumber() {
		return toAccountNumber;
	}
	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getFromAccountLastUpdateDate() {
		return fromAccountLastUpdateDate;
	}
	public void setFromAccountLastUpdateDate(String lastAccountUpdate) {
		this.fromAccountLastUpdateDate = lastAccountUpdate;
	}
}
