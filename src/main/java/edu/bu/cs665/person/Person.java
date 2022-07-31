package edu.bu.cs665.person;

import java.util.List;
import java.util.logging.Logger;

public class Person {
    private static Logger logger = Logger.getLogger(Person.class.getName());

    private List<SchoolIdentity> schoolIdentities;

    public Person() {
        logger.info("Person created");
    }
}
