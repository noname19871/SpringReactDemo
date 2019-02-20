package com.greglturnquist.payroll;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.math.BigDecimal;

@Data
@Entity
public class Account {

    private @Id
    @GeneratedValue
    Long id;

    private String accNumber;

    private Date LastOperation;

    private BigDecimal balanc;

}
