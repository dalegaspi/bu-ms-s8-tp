package edu.bu.cs665;

import edu.bu.cs665.course.*;
import edu.bu.cs665.exceptions.InvalidClassOfferingState;
import edu.bu.cs665.person.Person;
import edu.bu.cs665.program.Program;

import java.awt.event.WindowStateListener;
import java.util.List;
import java.util.logging.Logger;

/**
 * The Computer Science Department
 *
 * @see Department
 * @author dlegaspi@bu.edu
 */
public final class ComputerScienceDepartment extends Department {
    private static final Logger logger = Logger.getLogger(ComputerScienceDepartment.class.getName());

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
        public void addCoursesAndConcentrations() {
            var cs521 = instance.createCoreCourse("CS521", "Information Structures with Python",
                            "Information Structures with Python");
            var cs526 = instance.createCoreCourse("CS526", "Data Structures and Algorithms",
                            "Data Structures and Algorithms");
            var cs622 = instance.createCoreCourse("CS622", "Advanced Programming Techniques",
                            "Advanced Programming Techniques");
            var cs665 = instance.createCoreCourse("CS665", "Software Design and Patterns",
                            "Software Design and Patterns");
            var cs682 = instance.createCoreCourse("CS82", "Information Systems Analysis and Design",
                            "Information Systems Analysis and Design");
            var cs601 = instance.createElectiveCourse("CS601", "Web Application Development",
                            "Web Application Development");
            var cs669 = instance.createElectiveCourse("CS669", "Database Design", "Database Design");
            var cs683 = instance.createElectiveCourse("CS683", "Mobile Application Development with Android",
                            "Mobile Application Development with Android");

            var cg01 = new CourseGroup("Systems and Algorithm", List.of(cs526, cs665, cs682));
            var cg02 = new CourseGroup("Programming Languages", List.of());
            var cg03 = new CourseGroup("Object-Oriented Programming", List.of(cs521, cs622));
            var cg04 = new CourseGroup("Mobile and Web Development", List.of(cs601, cs683));
            var cg05 = new CourseGroup("Databases", List.of(cs669));

            var c01 = new Concentration(cg01);
            var c02 = new Concentration(cg02);
            var c03 = new Concentration(cg03);
            var c04 = new Concentration(cg04);
            var c05 = new Concentration(cg05);

            // sub-concentrations
            c02.add(c03);
            c02.add(c04);

            // top-level concentrations are for the chairperson
            cg01.setCoordinator(instance.getChairPerson());
            cg02.setCoordinator(instance.getChairPerson());
            cg05.setCoordinator(instance.getChairPerson());

            cg03.setCoordinator(instance.findFaculty("Henry Pym").orElseThrow());
            cg04.setCoordinator(instance.findFaculty("Wanda Maximoff").orElseThrow());

            instance.addConcentration(c01);
            instance.addConcentration(c02);
            instance.addConcentration(c05);
        }

        @Override
        public void addFaculty() {
            // set chair person
            var chairPerson = Person.createChairPerson("Tony Stark");
            instance.setChairPerson(chairPerson);

            // set advisors
            var gradAdvisor = Person.createFaculty("Peter Parker", true);
            Person.setAsAdvisor(gradAdvisor);
            instance.addGraduateAdvisor(SchoolYear.fromYear(2022), gradAdvisor);

            var underGradAdvisor = Person.createFaculty("Steve Rogers", true);
            Person.setAsAdvisor(underGradAdvisor);
            instance.addUnderGraduateAdvisor(SchoolYear.fromYear(2022), underGradAdvisor);

            // full-time faculty
            instance.addFaculty(Person.createFaculty("Thor Odinson", true));
            instance.addFaculty(Person.createFaculty("Suresh Kalathur", true));
            instance.addFaculty(Person.createFaculty("Wanda Maximoff", true));

            // part-time faculty
            instance.addFaculty(Person.createFaculty("Pietro Maximoff", false));
            instance.addFaculty(Person.createFaculty("Simon Williams", false));
            instance.addFaculty(Person.createFaculty("Henry Pym", false));
        }

        @Override
        public void addPrograms() {
            instance.addProgram(Program.createCertificateProgram("Web Application Development"));
            instance.addProgram(Program.createUndergraduateProgram("Computer Science", 6, 2));
            instance.addProgram(Program.createGraduateProgram("Software Development", 2, 2));
        }

        @Override
        public void addClassOfferings() throws InvalidClassOfferingState {
            var sy2022 = SchoolYear.fromYear(2022);

            var faculty01 = instance.findFaculty("Thor Odinson").orElseThrow();
            var faculty02 = instance.findFaculty("Suresh Kalathur").orElseThrow();

            var cs526 = instance.findCourse("CS526").orElseThrow();
            var cs669 = instance.findCourse("CS669").orElseThrow();
            var cs683 = instance.findCourse("CS683").orElseThrow();
            var cs665 = instance.findCourse("CS665").orElseThrow();

            instance.getRegistrar().createClassOffering(cs526, faculty01, sy2022.getSemester(1),
                            DEFAULT_ENROLLMENT_LIMIT);
            instance.getRegistrar().createClassOffering(cs665, faculty02, sy2022.getSemester(1),
                            DEFAULT_ENROLLMENT_LIMIT);
            instance.getRegistrar().createClassOffering(cs683, faculty01, sy2022.getSemester(2),
                            DEFAULT_ENROLLMENT_LIMIT);
            instance.getRegistrar().createClassOffering(cs669, faculty02, sy2022.getSemester(2),
                            DEFAULT_ENROLLMENT_LIMIT);
        }

        public ComputerScienceDepartment build() {
            return instance;
        }
    }
}
