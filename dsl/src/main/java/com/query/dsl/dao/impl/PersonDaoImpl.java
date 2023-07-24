package com.query.dsl.dao.impl;

import com.query.dsl.dao.PersonDao;
import com.query.dsl.entities.Person;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.query.dsl.entities.QPersonTry;

import com.querydsl.sql.Configuration;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLTemplates;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Person save(final Person person) {
        em.persist(person);
        return person;
    }

    @Override
    public String getQueryString()
    {
        SQLTemplates templates = MySQLTemplates.builder()
                .printSchema()
                .build();
        Configuration configuration = new Configuration(templates);

        final QPersonTry person= QPersonTry.person;
        Connection conn;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/database1","root","password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        SQLQuery sqlQuery = (SQLQuery) new SQLQuery(conn, configuration)
                .select(person.age,person.firstname,person.surname)
                .from(person).limit(10);
        sqlQuery.setUseLiterals(true);
        String query = sqlQuery.getSQL().getSQL();
        return query;
    }

    @Override
    public List<Person> findAllPersonQueryDSL() {
        final JPAQuery<Person> query= new JPAQuery<>(em);
        final QPersonTry person= QPersonTry.person;
        return query.from(person).fetch();
    }

    @Override
    public List<Person> findPersonsByFirstnameQueryDSL(final String firstname) {
        final JPAQuery<Person> query = new JPAQuery<>(em);
        final QPersonTry person = QPersonTry.person;

        return query.from(person).where(person.firstname.eq(firstname)).fetch();
    }

    @Override
    public List<Person> findPersonsByFirstnameAndSurnameQueryDSL(final String firstname, final String surname) {
        final JPAQuery<Person> query = new JPAQuery<>(em);
        final QPersonTry person = QPersonTry.person;
        return query.from(person).where(person.firstname.eq(firstname).and(person.surname.eq(surname))).fetch();
    }

    @Override
    public List<Person> findPersonsByFirstnameInDescendingOrderQueryDSL(final String firstname) {
        final JPAQuery<Person> query = new JPAQuery<>(em);
        final QPersonTry person = QPersonTry.person;

        return query.from(person).where(person.firstname.eq(firstname)).orderBy(person.surname.desc()).fetch();
    }

    @Override
    public int findMaxAge() {
        final JPAQuery<Person> query = new JPAQuery<>(em);
        final QPersonTry person = QPersonTry.person;

        return query.from(person).select(person.age.max()).fetchFirst();
    }

    @Override
    public Map<String, Integer> findMaxAgeByName() {
        final JPAQueryFactory query = new JPAQueryFactory(JPQLTemplates.DEFAULT, em);
        final QPersonTry person = QPersonTry.person;

        return query.from(person).transform(GroupBy.groupBy(person.firstname).as(GroupBy.max(person.age)));
    }

}
