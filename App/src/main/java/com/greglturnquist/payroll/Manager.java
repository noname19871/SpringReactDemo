package com.greglturnquist.payroll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Manager {

	private @Id @GeneratedValue Long id;

	private String name;

	private String firstName;

	private String lastName;

	protected Manager () {}

	public Manager(String name, String firstName, String lastName)
	{
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
	}
}

