package com.ingenico.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenico.model.Account;
import com.ingenico.model.Address;
import com.ingenico.model.Customer;
import com.ingenico.model.Transaction;
import com.ingenico.repository.CustomerRepository;
import com.ingenico.request.CustomerRequest;
import com.ingenico.request.TransferRequest;
import com.ingenico.response.CustomerResponse;
import com.ingenico.response.TransferResponse;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    
	@Override
	public CustomerResponse createCustomer(CustomerRequest accountRequest) {
		Customer customer = accountRequest.getCustomer();
		Date date =new Date();
		customer.setCreatedDate(date);
		for(Account account : customer.getAccounts())
		{
			account.setDateOpened(date);
			account.setLastUpdateDate(date);
			account.setCustomer(customer);
		}
		for(Address address : customer.getAddresses())
		{
			address.setCreatedDate(date);
			address.setCustomer(customer);
		}
		customer = customerRepository.save(customer);
		CustomerResponse accountResponse = new CustomerResponse();
		accountResponse.setCustomerId(customer.getId());
		return accountResponse;
	}

	@Override
	@Transactional
	public TransferResponse transferMoney(TransferRequest transferRequest) throws Exception {
		Customer fromCustomer = customerRepository.findOne(transferRequest.getFromCustomerId());
		Date transferRequestLastUpdDt = new SimpleDateFormat().parse(transferRequest.getFromAccountLastUpdateDate());
		Date date =new Date();
		addTransactionToFromAccount(fromCustomer,date,transferRequest.getFromAccountNumber(),transferRequestLastUpdDt,transferRequest.getAmount());
		Customer toCustomer = customerRepository.findOne(transferRequest.getToCustomerId());
		addTransactionToRecepientAccount(toCustomer,date,transferRequest.getToAccountNumber(),transferRequest.getAmount());
		customerRepository.save(fromCustomer);
		customerRepository.save(toCustomer);
		TransferResponse transferResponse = new TransferResponse();
		transferResponse.setStatus("Transfered");
		return transferResponse;
	}
	
	private void addTransactionToFromAccount(Customer customer,Date date,String accountNumber,Date transferRequestLastUpdDt,double amount) throws Exception
	{
		for(Account account :customer.getAccounts())
		{
			if(account.getAccountNumber().equals(accountNumber))
			{
				Date accountLastUpdDate = new SimpleDateFormat().parse(account.getLastUpdateDate().toString());
				//updations happens only if you have latest record and amount to send should be less than your balance amount
				if(!transferRequestLastUpdDt.equals(accountLastUpdDate) || amount > account.getBalance())
				{
					throw new RuntimeException("Invalid Request");
				}
				else
				{
					Transaction transferTxn = new Transaction();
					transferTxn.setAccount(account);
					transferTxn.setAmountOfTransaction(amount);
					transferTxn.setDateOfTransaction(date);
					account.addTransaction(transferTxn);
					account.setBalance(account.getBalance() - amount);
					account.setLastUpdateDate(date);
				}
			}
		}
	}
	
	private void addTransactionToRecepientAccount(Customer customer,Date date,String accountNumber,double amount)
	{
		for(Account toAccount :customer.getAccounts())
		{
			if(toAccount.getAccountNumber().equals(accountNumber))
			{
				Transaction toTransferTxn = new Transaction();
				toTransferTxn.setAccount(toAccount);
				toTransferTxn.setAmountOfTransaction(amount);
				toTransferTxn.setDateOfTransaction(date);
				toAccount.addTransaction(toTransferTxn);
				toAccount.setBalance(toAccount.getBalance() + amount);
				toAccount.setLastUpdateDate(date);
			
			}
		}
	}

}
