package edu.bu.cs665.hw03;

import edu.bu.cs665.BostonUniversity;
import edu.bu.cs665.person.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentSimulationTests {

    @Test
    @DisplayName("Basic tests")
    public void basicTests() {
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
