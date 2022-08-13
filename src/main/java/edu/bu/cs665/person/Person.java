package edu.bu.cs665.person;

import edu.bu.cs665.entity.AbstractEntity;
import edu.bu.cs665.messaging.Mailbox;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.logging.Logger;

/**
 * Base class for Student and School employee
 *
 * @see Student
 * @see Employee
 * @see Faculty
 * @author dlegaspi@bu.edu
 */
public class Person extends AbstractEntity {
    private static Logger logger = Logger.getLogger(Person.class.getName());

    private final String name;

    private final Mailbox mailbox;

    public Person(String name) {
        this.name = name;
        this.mailbox = new Mailbox(this);
    }

    public String getName() {
        return name;
    }

    public static Student createStudent(String name) {
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

    public static void setAsAdvisor(@NonNull Faculty faculty) {
        faculty.getTitles().add(Advisor.getInstance());
    }

    public static Employee createEmployee(String name) {
        return new Employee(name);
    }

    public Mailbox getMailbox() {
        return mailbox;
    }
}
