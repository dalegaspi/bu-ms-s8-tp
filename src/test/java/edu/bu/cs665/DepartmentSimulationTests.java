package edu.bu.cs665;

import edu.bu.cs665.BostonUniversity;
import edu.bu.cs665.course.Course;
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
    public void departmentCreation() {
        var bu = BostonUniversity.getInstance();

        var cs = bu.findDepartment("Computer Science");

        assertTrue(cs.isPresent());

    }

    @Test
    @DisplayName("Enrollment Simulation")
    public void enrollmentSimulation() {
        var p = new Student("Jack Sparrow");
    }

}
