package edu.bu.cs665.person;

import edu.bu.cs665.AbstractEntity;

import java.util.logging.Logger;

public class Person extends AbstractEntity {
    private static Logger logger = Logger.getLogger(Person.class.getName());

    private String name;

    public Person(String name) {
        this.name = name;
        logger.info("Person created");
    }

    public String getName() {
        return name;
    }
}
