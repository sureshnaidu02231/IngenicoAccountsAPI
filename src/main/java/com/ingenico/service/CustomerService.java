package com.ingenico.service;

import com.ingenico.request.CustomerRequest;
import com.ingenico.request.TransferRequest;
import com.ingenico.response.CustomerResponse;
import com.ingenico.response.TransferResponse;

public interface CustomerService {

	public CustomerResponse createCustomer(CustomerRequest accountRequest);
	public TransferResponse transferMoney(TransferRequest transferRequest) throws Exception;
}
