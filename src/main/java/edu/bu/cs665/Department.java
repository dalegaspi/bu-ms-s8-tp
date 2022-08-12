package edu.bu.cs665;

import edu.bu.cs665.course.ConcentrationGroup;
import edu.bu.cs665.course.SchoolYear;
import edu.bu.cs665.person.Employee;
import edu.bu.cs665.person.Faculty;
import edu.bu.cs665.person.Student;
import edu.bu.cs665.program.Program;

import java.util.*;

/**
 * Abstract class for university department
 *
 * @author dlegaspi@bu.edu
 */
public abstract class Department {
    private Employee chairPerson;
    private List<Employee> faculty = new ArrayList<>();
    private Map<SchoolYear, Faculty> graduateAdvisors = new HashMap<>();
    private Map<SchoolYear, Faculty> underGraduateAdvisors = new HashMap<>();

    protected final Registrar registrar = Registrar.getInstance();

    private final List<ConcentrationGroup> concentrationGroups = new ArrayList<>();
    private List<Program> programs;

    private final List<ClassOffering> classOfferings = new ArrayList<>();

    private final List<Student> students = new ArrayList<>();

    public Employee getChairPerson() {
        return chairPerson;
    }

    public void setChairPerson(Employee chairPerson) {
        this.chairPerson = chairPerson;
    }

    public List<Employee> getFaculty() {
        return faculty;
    }

    public void setFaculty(List<Employee> faculty) {
        this.faculty = faculty;
    }

    public Optional<Faculty> getGraduateAdvisor(SchoolYear year) {
        return Optional.ofNullable(graduateAdvisors.get(year));
    }

    public void addGraduateAdvisor(SchoolYear year, Faculty faculty) {
        graduateAdvisors.put(year, faculty);
    }

    public Optional<Faculty> getUnderGraduateAdvisor(SchoolYear year) {
        return Optional.ofNullable(underGraduateAdvisors.get(year));
    }

    public void addUnderGraduateAdvisor(SchoolYear year, Faculty faculty) {
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
        return classOfferings;
    }

    public List<Student> getStudents() {
        return students;
    }
}
