package com.agung.spring.jdbc;

import com.agung.spring.jdbc.entity.Address;
import com.agung.spring.jdbc.entity.Customer;
import com.agung.spring.jdbc.repository.AddressRepository;
import com.agung.spring.jdbc.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppRepositoryConfig.class)
@AutoConfigureDataJdbc
@Slf4j
public class CustomerRepositoryTest {

    @Autowired
    public  CustomerRepository customerRepository;

    @Autowired
    public AddressRepository addressRepository;

    @Before
    public void populateData(){
        log.debug("run populate data");

        Address address = new Address();
        address.setId(1);
        address.setLocation("Nganjuk");
        address.setProvince("Jawa Timur");
        for (int i=0; i<10; i++){
            Customer customers = new Customer(null,"agung pramono"+i,"agung"+i+"@mail.com");
            customerRepository.save(customers);
//            address.setCustomer(customers);
//            addressRepository.save(address);
        }
    }

    @After
    public void deletePopulateData(){
        log.debug("run delete data");
        customerRepository.deleteAll();
    }

    @Test
    public void test(){
        Optional<Customer> customer = customerRepository.findById(1);
        assertNotNull(customer);
    }

    @Test
    public void findAllTest(){
        List<Customer> customers = customerRepository.findAll();
        assertNotNull(customers);
    }

    @Test
    public void testCount(){
        long customerCount = customerRepository.count();
        assertEquals(10, customerCount);
    }

    @Test
    public void testFindByName(){
        Customer customer = customerRepository.findCustomerByName("agung pramono3");
        assertNotNull(customer);
        assertEquals("agung pramono3", customer.getName());
        assertNotEquals("agung pramono21", customer.getName());
    }

    @Test
    public void testFindByEmail(){
        Customer customer = customerRepository.findByEmail("agung3@mail.com");
        assertNotNull(customer);
        assertEquals("agung3@mail.com",customer.getEmail());
        assertNotEquals("agung87@mail.com", customer.getEmail());
    }

    @Test
    public void testFindName(){
        String name = customerRepository.findName("agung2422");
        assertNull(name);
        String name2 = customerRepository.findName("agung pramono7");
        assertNotNull(name2);
        assertEquals("agung pramono7", name2);
    }
}