package com.agung.spring.jdbc.repository;

import com.agung.spring.jdbc.entity.Address;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressRepository extends PagingAndSortingRepository<Address, Integer> {
}
