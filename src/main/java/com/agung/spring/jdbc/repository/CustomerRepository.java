package com.agung.spring.jdbc.repository;

import com.agung.spring.jdbc.entity.Customer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

    List<Customer> findAll();

    @Query("select * from customer where name= :name")
    Customer findCustomerByName(@Param("name") String name);

    Customer findByEmail(String email);

    @Query("select name from customer where name= :name")
    String findName(@Param("name") String name);
}
