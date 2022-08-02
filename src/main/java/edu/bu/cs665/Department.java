package edu.bu.cs665;

import edu.bu.cs665.person.Person;
import edu.bu.cs665.person.employee.Employee;
import edu.bu.cs665.program.Program;

import java.util.List;

public abstract class Department {
    protected Employee chairPerson;
    protected List<Employee> faculty;
    protected Employee graduateAdvisor;
    protected Employee underGraduateAdvisor;

    List<Program> programs;
}
