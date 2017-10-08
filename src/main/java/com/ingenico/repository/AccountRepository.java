package com.ingenico.repository;

import org.springframework.data.repository.CrudRepository;

import com.ingenico.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{

}
