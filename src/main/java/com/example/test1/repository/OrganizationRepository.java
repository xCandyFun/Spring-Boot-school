package com.example.test1.repository;

import com.example.test1.entity.Organization;
import org.springframework.data.repository.ListCrudRepository;

public interface OrganizationRepository extends ListCrudRepository <Organization, Long>{

}

