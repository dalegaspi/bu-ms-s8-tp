package edu.bu.cs665;

import edu.bu.cs665.course.ClassOffering;
import edu.bu.cs665.course.Course;
import edu.bu.cs665.course.SchoolYear;
import edu.bu.cs665.exceptions.InvalidClassOfferingState;
import edu.bu.cs665.person.Person;
import edu.bu.cs665.program.Program;

import java.awt.event.WindowStateListener;
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
            instance.createCoreCourse("CS521", "Information Structures with Python",
                            "Information Structures with Python");
            instance.createCoreCourse("CS526", "Data Structures and Algorithms", "Data Structures and Algorithms");
            instance.createCoreCourse("CS622", "Advanced Programming Techniques",
                            "Advanced Programming Techniques");
            instance.createCoreCourse("CS665", "Software Design and Patterns",
                            "Software Design and Patterns");
            instance.createCoreCourse("CS82", "Information Systems Analysis and Design",
                            "Information Systems Analysis and Design");
            instance.createElectiveCourse("CS601", "Web Application Development", "Web Application Development");
            instance.createElectiveCourse("CS669", "Database Design", "Database Design");
            instance.createElectiveCourse("CS683", "Mobile Application Development with Android",
                            "Mobile Application Development with Android");
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
            instance.addProgram(Program.createUndergraduateProgram("Computer Science", 6, 2));
            instance.addProgram(Program.createGraduateProgram("Software Development", 2, 2));
        }

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        @Override
        public void addClassOfferings(SchoolYear year) throws InvalidClassOfferingState {
            var sy2021 = SchoolYear.fromYear(2021);
            var sy2022 = SchoolYear.fromYear(2022);

            var faculty01 = instance.findFaculty("Thor Odinson").get();
            var faculty02 = instance.findFaculty("Maya Lopez").get();

            var cs526 = instance.findCourse("CS526").get();
            var cs669 = instance.findCourse("CS669").get();

            var offering01 = instance.getRegistrar()
                            .createClassOffering(cs526, faculty01, sy2022.getSemester(1), DEFAULT_ENROLLMENT_LIMIT);
            var offering02 = instance.getRegistrar()
                            .createClassOffering(cs669, faculty02, sy2022.getSemester(2), DEFAULT_ENROLLMENT_LIMIT);
        }

        public ComputerScienceDepartment build() {
            return instance;
        }
    }
}
