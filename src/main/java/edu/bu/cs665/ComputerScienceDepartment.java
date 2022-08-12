package edu.bu.cs665;

import edu.bu.cs665.course.SchoolYear;
import edu.bu.cs665.person.Person;
import edu.bu.cs665.program.Program;

import java.awt.event.WindowStateListener;

/**
 * The Computer Science Department
 *
 * @see Department
 * @author dlegaspi@bu.edu
 */
public final class ComputerScienceDepartment extends Department {
    private static final ComputerScienceDepartment instance = new ComputerScienceDepartment();

    public static Department getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "Computer Science";
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Department> DepartmentBuilder<T> getBuilder() {
        return (DepartmentBuilder<T>) new ComputerScienceDepartmentBuilder();
    }

    public static class ComputerScienceDepartmentBuilder implements DepartmentBuilder<ComputerScienceDepartment> {

        private final ComputerScienceDepartment instance = new ComputerScienceDepartment();

        @Override
        public void addConcentrations() {

        }

        @Override
        public void addFaculty() {
            // set chair person
            var chairPerson = Person.createChairPerson("Tony Stark");
            instance.setChairPerson(chairPerson);

            // set advisors
            var gradAdvisor = Person.createFaculty("Peter Parker", true);
            Person.setAsAdvisor(gradAdvisor);
            instance.addGraduateAdvisor(SchoolYear.fromCurrentYear(), gradAdvisor);

            var underGradAdvisor = Person.createFaculty("Steve Rogers", true);
            Person.setAsAdvisor(underGradAdvisor);
            instance.addUnderGraduateAdvisor(SchoolYear.fromCurrentYear(), underGradAdvisor);

            // full-time faculty
            instance.addFaculty(Person.createFaculty("Thor Odinson", true));
            instance.addFaculty(Person.createFaculty("Henry Pym", true));
            instance.addFaculty(Person.createFaculty("Wanda Maximoff", true));

            // part-time faculty
            instance.addFaculty(Person.createFaculty("Pietro Maximoff", false));
            instance.addFaculty(Person.createFaculty("Simon Williams", false));
            instance.addFaculty(Person.createFaculty("Maya Lopez", false));
        }

        @Override
        public void addPrograms() {
            instance.addProgram(Program.createCertificateProgram("Chocolate Boiler Repair"));
        }

        @Override
        public void addCourses(SchoolYear year) {

        }

        public ComputerScienceDepartment build() {
            return instance;
        }
    }
}
