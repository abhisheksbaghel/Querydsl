package com.query.dsl.controllers;


import com.query.dsl.entities.Person;
import com.query.dsl.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/add")
    public Person save(@RequestBody Person person)
    {
        return personService.save(person);
    }

    @GetMapping("/get-all")
    public List<Person> getAll()
    {
        return personService.findAll();
    }

    @GetMapping("/get-string")
    public String getString()
    {
        return personService.getQueryString();
    }
}
