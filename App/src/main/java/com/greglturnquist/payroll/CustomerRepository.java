package com.greglturnquist.payroll;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;


@PreAuthorize("hasRole('ROLE_MANAGER')")
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

	@Override
	@PreAuthorize("#customer?.manager == null or #customer?.manager?.name == authentication?.name")
    Customer save(@Param("customer") Customer customer);

	@Override
	@PreAuthorize("@CustomerRepository.findById(#id)?.manager?.name == authentication?.name")
	void deleteById(@Param("id") Long id);

	@Override
	@PreAuthorize("#customer?.manager?.name == authentication?.name")
	void delete(@Param("customer") Customer customer);

}
