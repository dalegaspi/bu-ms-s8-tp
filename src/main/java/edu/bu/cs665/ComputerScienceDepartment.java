package edu.bu.cs665;

import edu.bu.cs665.course.Concentration;
import edu.bu.cs665.course.Course;
import edu.bu.cs665.course.SchoolYear;
import edu.bu.cs665.person.Faculty;
import edu.bu.cs665.program.Program;

import java.util.List;

/**
 * The Computer Science Department
 *
 * @see Department
 * @author dlegaspi@bu.edu
 */
public final class ComputerScienceDepartment extends Department {
    public static class ComputerScienceDepartmentBuilder {
        ComputerScienceDepartment instance = new ComputerScienceDepartment();

        void addProgram(Program program) {

        }

        void addCourse(Course course) {

        }

        void addConcentration(Concentration concentration, List<Concentration> subConcentrations) {

        }

        void addCourseOffering(SchoolYear schoolYear, Course course, Faculty professor) {
            // todo
        }

        Department build() {
            return instance;
        }
    }
}
