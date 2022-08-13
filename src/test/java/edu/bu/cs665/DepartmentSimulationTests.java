package edu.bu.cs665;

import edu.bu.cs665.course.Course;
import edu.bu.cs665.exceptions.InvalidEnrollmentRequest;
import edu.bu.cs665.exceptions.InvalidRecipientException;
import edu.bu.cs665.person.Person;
import edu.bu.cs665.person.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentSimulationTests {

    @Test
    @DisplayName("Basic Tests")
    public void basicTests() {
        var bu = BostonUniversity.getInstance();

        var cs = bu.findDepartment("Computer Science");

        assertTrue(cs.isPresent());

        // testing equality
        Course c1 = new Course("CS526", "Data Structures and Algorithms", "Data Structures and Algorithms");
        Course c2 = new Course("CS526", "Data Structures and Algorithms", "Data Structures and Algorithms");

        assertEquals(c1, c2);
    }

    @Test
    @DisplayName("Department Creation Tests")
    public void departmentCreation() throws InvalidEnrollmentRequest, InvalidRecipientException {
        var bu = BostonUniversity.getInstance();

        var cs = bu.findDepartment("Computer Science").orElseThrow();

        var s = Person.createStudent("Robert Baratheon");
        cs.enrollProgram(s, "Chocolate Boiler Repair");

        assertNotNull(s.getProgram());
        // testing the sending of query
        cs.sendMessageToChairPerson(s, "Hello", "World");
        assertTrue(cs.getChairPerson().getMailbox().hasMessages());
    }

    @Test
    @DisplayName("Enrollment Simulation")
    public void enrollmentSimulation() {
        var p = new Student("Jack Sparrow");
    }

}
