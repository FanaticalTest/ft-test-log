package fttestlog.repository;

import org.springframework.data.repository.CrudRepository;

import fttestlog.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
}
