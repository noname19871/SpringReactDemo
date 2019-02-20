package com.greglturnquist.payroll;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Address {

    private @Id
    @GeneratedValue
    Long id;

    private String city;

    private String street;

    private String flat;
}
