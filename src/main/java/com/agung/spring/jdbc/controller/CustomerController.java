package com.agung.spring.jdbc.controller;


import com.agung.spring.jdbc.entity.Customer;
import com.agung.spring.jdbc.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer/list")
    public Page<Customer> allCustomer(Pageable pageable){
        return customerRepository.findAll(pageable);
    }

    @GetMapping(value = "/customer", params = "name")
    public ResponseEntity<Customer> customerByName(@RequestParam(value = "name", required = true) String name){
        return ResponseEntity.ok(customerRepository.findCustomerByName(name));
    }

    @GetMapping(value = "/customer", params = "id")
    public ResponseEntity<Customer> findById(@RequestParam(value = "id", required = true) Integer id){
        Optional<Customer> c = customerRepository.findById(id);
        if (c.isPresent()){
            return ResponseEntity.ok(c.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        return ResponseEntity.created(null).body(customerRepository.save(customer));
    }

    @PutMapping("/customer")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        Optional<Customer> c = customerRepository.findById(customer.getId());
        if (c.isPresent()){
            BeanUtils.copyProperties(customer, c.get());
            ResponseEntity.ok(customerRepository.save(c.get()));
        }
        return ResponseEntity.badRequest().body(customer);
    }

    @DeleteMapping("/customer")
    public ResponseEntity<Customer> deleteCustomer(@RequestBody Customer customer){
        Optional<Customer> c = customerRepository.findById(customer.getId());
        if (c.isPresent()){
            customerRepository.delete(c.get());
            return ResponseEntity.ok(c.get());
        }
        return ResponseEntity.notFound().build();
    }
}
