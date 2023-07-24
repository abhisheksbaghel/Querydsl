package com.query.dsl.dao;

import com.query.dsl.entities.Person;

import java.util.List;
import java.util.Map;

public interface PersonDao {

    public Person save(Person person);

    public List<Person> findAllPersonQueryDSL();

    public List<Person> findPersonsByFirstnameQueryDSL(String firstname);

    public List<Person> findPersonsByFirstnameAndSurnameQueryDSL(String firstname, String surname);

    public List<Person> findPersonsByFirstnameInDescendingOrderQueryDSL(String firstname);

    public int findMaxAge();

    public Map<String, Integer> findMaxAgeByName();

    public String getQueryString();

}
