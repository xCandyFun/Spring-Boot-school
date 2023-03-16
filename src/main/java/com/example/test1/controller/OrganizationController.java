package com.example.test1.controller;

import com.example.test1.entity.Organization;
import com.example.test1.entity.Person;
import com.example.test1.repository.OrganizationRepository;
import com.example.test1.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/orgs")
public class OrganizationController {

    private final OrganizationRepository orgRepo;
    private final PersonRepository personRepo;

    public OrganizationController(OrganizationRepository organizationRepository, PersonRepository personRepository){
        orgRepo = organizationRepository;
        personRepo = personRepository;
    }

    @PostMapping
    void addOrg(@RequestBody Organization org){
        var copyOfPersons = Set.copyOf(org.getPersons());
        org.getPersons().clear();
        org.getPersons().addAll(personRepo.saveAll(copyOfPersons));
        orgRepo.save(org);
    }

    @GetMapping("/{id}")
    Organization getById(@PathVariable Long id){
        return orgRepo.findById(id).orElseThrow();
    }

    @GetMapping
    List<Organization> getOrgs(){
        return orgRepo.findAll();
    }

    @DeleteMapping("/{id}")
    void deleteOrg(@PathVariable Long id){
        orgRepo.deleteById(id);
    }

    @PostMapping("/{orgId}/persons")
    @Transactional
    void addPerson(@RequestBody Person person, @PathVariable Long orgId){
        var savedPerson = personRepo.save(person);
        var org = orgRepo.findById(orgId).orElseThrow();
        org.getPersons().add(savedPerson);
    }

    @DeleteMapping("/{orgId}/persons/{personId}")
    @Transactional
    void deletePerson(@PathVariable Long orgId, @PathVariable Long personId){
        orgRepo.findById(orgId).ifPresent(o-> o.getPersons().remove(personRepo.findById(personId).orElseThrow()));
    }


}
