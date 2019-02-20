package com.greglturnquist.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final CustomerRepository customers;
	private final ManagerRepository managers;
	private final AccountRepository accounts;
	private final AddressRepository addresses;
	private final OperationRepository operations;
	private final VisitorRepository visitors;

	@Autowired
	public DatabaseLoader(CustomerRepository customerRepository,
						  ManagerRepository managerRepository,
						  AccountRepository accountRepository,
						  AddressRepository addressRepository,
						  OperationRepository operationRepository,
						  VisitorRepository visitorRepository) {

		this.customers = customerRepository;
		this.managers = managerRepository;
		this.accounts = accountRepository;
		this.addresses = addressRepository;
		this.operations = operationRepository;
		this.visitors = visitorRepository;
	}

	@Override
	public void run(String... strings) throws Exception {

		this.visitors.save(new Visitor("manager1", "12345",
							"ROLE_MANAGER"));
		this.visitors.save(new Visitor("manager2", "67890",
							"ROLE_MANAGER"));

		Manager ivan = this.managers.save((new Manager("manager1", "Иван", "Иванович")));

		Manager andrew = this.managers.save((new Manager("manager2", "Андрей", "Андреевич")));

		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("manager1", "doesn't matter",
				AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

		this.customers.save(new Customer("1", "1", ivan));
		this.customers.save(new Customer("2", "2", ivan));
		this.customers.save(new Customer("3", "3", ivan));


		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("manager2", "doesn't matter",
				AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

		this.customers.save(new Customer("4", "4", andrew));
		this.customers.save(new Customer("5", "5", andrew));
		this.customers.save(new Customer("6", "6", andrew));

		SecurityContextHolder.clearContext();
	}
}