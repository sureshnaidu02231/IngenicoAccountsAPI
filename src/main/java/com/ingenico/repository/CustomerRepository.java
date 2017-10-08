package com.ingenico.repository;

import org.springframework.data.repository.CrudRepository;

import com.ingenico.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
