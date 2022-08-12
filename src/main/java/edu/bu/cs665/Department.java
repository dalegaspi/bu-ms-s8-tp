package edu.bu.cs665;

import edu.bu.cs665.course.ConcentrationGroup;
import edu.bu.cs665.course.SchoolYear;
import edu.bu.cs665.person.Employee;
import edu.bu.cs665.person.Faculty;
import edu.bu.cs665.person.Person;
import edu.bu.cs665.person.Student;
import edu.bu.cs665.program.Program;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract class for university department
 *
 * @author dlegaspi@bu.edu
 */
public abstract class Department {

    private static Logger logger = Logger.getLogger(Department.class.getName());

    public abstract String getName();

    private final DepartmentMailRoom mailroom;

    public Department() {
        mailroom = DepartmentMailRoom.getInstance(this, registrar);
    }

    private Faculty chairPerson;
    private List<Faculty> faculty = new ArrayList<>();
    private final Map<SchoolYear, Faculty> graduateAdvisors = new HashMap<>();
    private final Map<SchoolYear, Faculty> underGraduateAdvisors = new HashMap<>();

    protected final Registrar registrar = Registrar.getInstance();

    private final List<ConcentrationGroup> concentrationGroups = new ArrayList<>();
    private List<Program> programs;

    private final List<Student> students = new ArrayList<>();

    public Faculty getChairPerson() {
        return chairPerson;
    }

    public void setChairPerson(Faculty chairPerson) {
        logger.log(Level.INFO, "Setting {0} as Chairperson", chairPerson.getName());
        this.chairPerson = chairPerson;
        this.faculty.add(chairPerson);
    }

    public List<Faculty> getFaculty() {
        return faculty;
    }

    public void setFaculty(List<Faculty> faculty) {
        this.faculty = faculty;
    }

    public Optional<Faculty> getGraduateAdvisor(SchoolYear year) {
        return Optional.ofNullable(graduateAdvisors.get(year));
    }

    public void addGraduateAdvisor(SchoolYear year, Faculty faculty) {
        logger.log(Level.INFO, "Setting {0} as Graduate advisor for {1}", new Object[] {
                faculty.getName(), year });
        graduateAdvisors.put(year, faculty);
    }

    public Optional<Faculty> getUnderGraduateAdvisor(SchoolYear year) {
        return Optional.ofNullable(underGraduateAdvisors.get(year));
    }

    public void addUnderGraduateAdvisor(SchoolYear year, Faculty faculty) {
        logger.log(Level.INFO, "Setting {0} as Undergraduate advisor for {1}", new Object[] {
                faculty.getName(), year });
        this.underGraduateAdvisors.put(year, faculty);
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public List<ConcentrationGroup> getConcentrationGroups() {
        return concentrationGroups;
    }

    public List<ClassOffering> getClassOfferings() {
        return registrar.getClassOfferings();
    }

    public List<Student> getStudents() {
        return students;
    }

    public interface DepartmentBuilder<T extends Department> {
        void addConcentrations();

        void addFaculty();

        void addPrograms();

        void addCourses(SchoolYear year);

        T build();
    }

    public abstract <T extends Department> DepartmentBuilder<T> getBuilder();
}
