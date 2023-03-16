package com.example.test1;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Vector;

@RestController
@RequestMapping("/names")
public class PersonController {

    List<String> names = new Vector<>();

    @GetMapping("/{id}")
    String getAName(@PathVariable int id){
        return names.get(id);
    }

    @GetMapping()
    List<String> getNames(){
        return names;
    }

    @PostMapping
    String addName(@RequestBody String name){
        names.add(name);
        return name;
    }

    @GetMapping("lang")
    String preferedLanguage(@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String lang){
        return lang;
    }

}

