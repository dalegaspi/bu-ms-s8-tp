package edu.bu.cs665.person;

import edu.bu.cs665.AbstractEntity;
import edu.bu.cs665.person.employee.Employee;

import java.util.List;
import java.util.logging.Logger;

public class Person extends AbstractEntity {
    private static Logger logger = Logger.getLogger(Person.class.getName());

    public Person() {
        logger.info("Person created");
    }

}
