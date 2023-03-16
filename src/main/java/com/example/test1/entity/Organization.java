package com.example.test1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private  String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Person> persons = new HashSet<>();
}
