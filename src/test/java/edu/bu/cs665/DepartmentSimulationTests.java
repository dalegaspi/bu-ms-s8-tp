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

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentSimulationTests {
    private static final Logger logger = Logger.getLogger(DepartmentSimulationTests.class.getName());

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
    @DisplayName("Composite Tests")
    public void compositeTests() {
        // demonstrate the format() via composite
        cs.getConcentrations().forEach(c -> {
            logger.info(c.format(true));
        });
    }

    @Test
    @DisplayName("Department Creation Basic Functionality Tests")
    public void departmentCreationBasicFunctionalityTests() throws InvalidEnrollmentRequest, InvalidRecipientException {
        // test student enrollment to a program
        var sy2022 = SchoolYear.fromYear(2022);

        var s = Person.createStudent("Robert Baratheon");
        cs.enrollProgram(s, "Web Application Development");
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
    @DisplayName("Enrollment Waitlist Simulation")
    public void enrollmentWaitlistSimulation() throws InvalidEnrollmentRequest {
        var sy2022 = SchoolYear.fromYear(2022);

        var s1 = Person.createStudent("Jack Sparrow");
        var s2 = Person.createStudent("Hector Barbossa");
        var s3 = Person.createStudent("Will Turner");

        cs.enrollProgram(s1, "Software Development");
        cs.enrollProgram(s2, "Computer Science");
        cs.enrollProgram(s3, "Web Application Development");

        var co = r.findClassOffering("CS665", sy2022.getSemester(1)).orElseThrow();
        assertNotNull(co);

        // setup such that the ChairPerson's mailbox is empty
        cs.getChairPerson().getMailbox().clearMessages();
        assertFalse(cs.getChairPerson().getMailbox().hasMessages());

        // 665 is filled with Sparrown and Barbossa enrolling the class
        r.enrollCourse(s1, co);

        // this triggers notification to ChairPerson
        r.enrollCourse(s2, co);

        // check that the ChairPerson has a notification
        assertTrue(cs.getChairPerson().getMailbox().hasMessages());

        // this expects the exception as Turner trying to enroll and s3 gets
        // notification in the process
        assertThrows(InvalidEnrollmentRequest.class, () -> r.enrollCourse(s3, co));

        // Turner should have received some notification as result of waitlist
        assertTrue(s3.getMailbox().hasMessages());

        // Barbossa drops the course; this trigger auto-enrolling s3
        var s2ec = s2.findEnrolledCourse("CS665").orElseThrow();
        r.dropCourse(s2, s2ec);

        // Turner should now have enrolled in 665
        assertTrue(s3.findEnrolledCourse("CS665").isPresent());
    }

}
