package com.ingenico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ingenico.request.CustomerRequest;
import com.ingenico.request.TransferRequest;
import com.ingenico.response.CustomerResponse;
import com.ingenico.response.TransferResponse;
import com.ingenico.service.CustomerService;

@RestController
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    /**
     * 
     * @param accountRequest Request that contains the customer and account information to create an account
     * @return CustomerResponse return the customer id if it is created successfully
     */
    @RequestMapping("/Account/")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(@RequestBody CustomerRequest accountRequest) {
    	return customerService.createCustomer(accountRequest);
   }
    /**
     * 
     * @param transferRequest Transfer details of from customer and To Customer
     * @return TransferResponse Response is a string with value Transferred
     * @throws Exception if it cannot convert the String to date or date to string.
     */
    @RequestMapping("/Account/transfer")
    public TransferResponse transferAmount(@RequestBody TransferRequest transferRequest) throws Exception {
    	return customerService.transferMoney(transferRequest);
   }
    
}
