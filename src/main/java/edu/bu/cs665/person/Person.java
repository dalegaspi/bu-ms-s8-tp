package edu.bu.cs665.person;

import edu.bu.cs665.AbstractEntity;

import java.util.logging.Logger;

public class Person extends AbstractEntity {
    private static Logger logger = Logger.getLogger(Person.class.getName());

    private final String name;

    public Person(String name) {
        this.name = name;
        logger.info("Person created");
    }

    public String getName() {
        return name;
    }

    public static Person createStudent(String name) {
        return new Student(name);
    }

    public static Faculty createFaculty(String name, boolean fullTime) {
        var e = new Faculty(name);
        e.setFullTime(fullTime);
        return e;
    }

    public static Faculty createChairPerson(String name) {
        var e = createFaculty(name, false);
        e.getTitles().add(ChairPerson.getInstance());
        return e;
    }
}
