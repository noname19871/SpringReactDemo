package com.greglturnquist.payroll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


@Data
@Entity
public class Customer {

	private @Id @GeneratedValue Long id;

	private String firstName;

	private String lastName;

	private String name;

	private @OneToMany @JsonIgnore List<Address> addresses;

	private @OneToMany  @JsonIgnore  List<Account> accounts;

	private @Version @JsonIgnore Long version;

	private @ManyToOne Manager manager;

	private Customer() {}

	public Customer(String firstName, String lastName, Manager manager) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.manager = manager;
	}
}