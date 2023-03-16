package com.example.test1.repository;

import com.example.test1.entity.Person;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PersonRepository extends ListCrudRepository<Person,Long> {


}
