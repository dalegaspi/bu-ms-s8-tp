package edu.bu.cs665;

import edu.bu.cs665.course.Course;
import edu.bu.cs665.course.SchoolYear;
import edu.bu.cs665.exceptions.InvalidEnrollmentRequest;
import edu.bu.cs665.exceptions.InvalidRecipientException;
import edu.bu.cs665.grade.CourseGrade;
import edu.bu.cs665.person.Person;
import edu.bu.cs665.person.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentSimulationTests {

    static BostonUniversity bu;
    static Department cs;
    static Registrar r;

    @BeforeAll
    public static void init() {
        bu = BostonUniversity.getInstance();
        cs = bu.findDepartment("Computer Science").orElseThrow();
        r = cs.getRegistrar();
    }

    @Test
    @DisplayName("Basic Tests")
    public void basicTests() {

        // testing equality
        Course c1 = new Course("CS526", "Data Structures and Algorithms", "Data Structures and Algorithms");
        Course c2 = new Course("CS526", "Data Structures and Algorithms", "Data Structures and Algorithms");

        assertEquals(c1, c2);
    }

    @Test
    @DisplayName("Department Creation Basic Functionality Tests")
    public void departmentCreationBasicFunctionalityTests() throws InvalidEnrollmentRequest, InvalidRecipientException {
        // test student enrollment to a program
        var sy2022 = SchoolYear.fromYear(2022);

        var s = Person.createStudent("Robert Baratheon");
        cs.enrollProgram(s, "Chocolate Boiler Repair");
        assertNotNull(s.getProgram());

        var co = r.findClassOffering("CS526", sy2022.getSemester(1)).orElseThrow();
        r.enrollCourse(s, co);

        cs.assignGrade(s, "CS526", CourseGrade.A);

        s.emitFullStatus();

        var f = cs.findFaculty("Thor Odinson").orElseThrow();
        f.emitFullStatus(sy2022.getSemester(1));

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
