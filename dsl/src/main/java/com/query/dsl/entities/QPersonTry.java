package com.query.dsl.entities;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.processing.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QPerson is a Querydsl query type for Person
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPersonTry extends EntityPathBase<Person> {

    private static final long serialVersionUID = 977153982L;

    public static final QPersonTry person = new QPersonTry("person");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath firstname = createString("firstname");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath surname = createString("surname");

    public QPersonTry(String variable) {
        super(Person.class, forVariable(variable));
    }

    public QPersonTry(Path<? extends Person> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersonTry(PathMetadata metadata) {
        super(Person.class, metadata);
    }

}

