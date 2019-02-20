package com.greglturnquist.payroll;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface VisitorRepository extends Repository<Visitor, Long> {

    Visitor save(Visitor visitor);

    Visitor findByName(String name);
}
