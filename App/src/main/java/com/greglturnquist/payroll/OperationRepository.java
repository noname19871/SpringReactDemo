package com.greglturnquist.payroll;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface OperationRepository extends PagingAndSortingRepository<Operation, Long> {

}
