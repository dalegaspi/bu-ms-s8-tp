package edu.bu.cs665;

import edu.bu.cs665.person.Person;
import edu.bu.cs665.person.employee.Employee;
import edu.bu.cs665.program.Program;

import java.util.List;

/**
 * Abstract class for university department
 *
 * @author dlegaspi@bu.edu
 */
public abstract class Department {
    private Employee chairPerson;
    private List<Employee> faculty;
    private Employee graduateAdvisor;
    private Employee underGraduateAdvisor;

    private List<Program> programs;

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

    public Employee getGraduateAdvisor() {
        return graduateAdvisor;
    }

    public void setGraduateAdvisor(Employee graduateAdvisor) {
        this.graduateAdvisor = graduateAdvisor;
    }

    public Employee getUnderGraduateAdvisor() {
        return underGraduateAdvisor;
    }

    public void setUnderGraduateAdvisor(Employee underGraduateAdvisor) {
        this.underGraduateAdvisor = underGraduateAdvisor;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }
}
