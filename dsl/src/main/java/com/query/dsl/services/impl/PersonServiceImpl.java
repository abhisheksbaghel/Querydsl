package com.query.dsl.services.impl;

import com.query.dsl.dao.PersonDao;
import com.query.dsl.entities.Person;
import com.query.dsl.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public Person save(Person person) {
        return personDao.save(person);
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAllPersonQueryDSL();
    }

    @Override
    public String getQueryString()
    {
        return personDao.getQueryString();
    }
}
