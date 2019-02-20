package com.greglturnquist.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {

	private final VisitorRepository repository;

	@Autowired
	public SpringDataJpaUserDetailsService(VisitorRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Visitor visitor = this.repository.findByName(name);
		return new User(visitor.getName(), visitor.getPassword(),
				AuthorityUtils.createAuthorityList(visitor.getRoles()));
	}

}
