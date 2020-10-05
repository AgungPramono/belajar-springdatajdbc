package com.agung.spring.jdbc.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

@Data
public class Address {

    @Id
    private Integer id;
    private String location;
    private String province;

    @MappedCollection(idColumn = "id_customer", keyColumn = "id")
    private Customer customer;

    public Address() {
    }
}
