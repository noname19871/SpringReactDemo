package com.greglturnquist.payroll;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Operation {

    private @Id
    @GeneratedValue
    Long id;

    private @ManyToOne Account accDebit;

    private @ManyToOne Account accCredit;

    private BigDecimal amount;

    private Date dateOp;
}
