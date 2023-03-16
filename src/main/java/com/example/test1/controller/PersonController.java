package com.example.test1.controller;

import com.example.test1.entity.Person;
import com.example.test1.repository.PersonRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

@RestController
@RequestMapping("/names")
public class PersonController {

    private final PersonRepository repo;

    public PersonController(PersonRepository personRepository){
        repo=personRepository;
    }

    @GetMapping("/{id}")
    Person getAName(@PathVariable long id){
        return repo.findById(id).orElseThrow();
    }

    @GetMapping()
    List<Person> getNames(){
        return repo.findAll();
    }

    @PostMapping
    void addName(@RequestBody Person person){
        String name = person.getName();
        if (name == null || name.isEmpty())
            throw new IllegalStateException();
        repo.save(person);
    }

    @GetMapping("lang")
    String preferedLanguage(@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String lang){
        return lang;
    }

}

