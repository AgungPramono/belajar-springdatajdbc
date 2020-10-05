package com.agung.spring.jdbc.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Customer {

    @Id

    private Integer id;

    private String name;

    private String email;

//    @MappedCollection()
//    private Set<Address> addresses = new HashSet<>();

//    public Customer() {
//    }
//
    public Customer( Integer id,  String name,  String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
