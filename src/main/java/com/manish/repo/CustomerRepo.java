package com.manish.repo;

import org.springframework.data.repository.CrudRepository;

import com.manish.entity.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Integer>  {

	public Customer findByCname(String cname);
}
