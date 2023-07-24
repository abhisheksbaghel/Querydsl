package com.query.dsl.services;

import com.query.dsl.entities.Person;

import java.util.List;

public interface PersonService {

    Person save(Person person);

    List<Person> findAll();

    public String getQueryString();

}
