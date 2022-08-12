package edu.bu.cs665;

import edu.bu.cs665.course.ConcentrationGroup;
import edu.bu.cs665.course.SchoolYear;
import edu.bu.cs665.person.Faculty;
import edu.bu.cs665.person.Student;
import edu.bu.cs665.program.Program;
import org.checkerframework.checker.nullness.qual.NonNull;

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
    private final Set<Faculty> faculty = new HashSet<>();
    private final Map<SchoolYear, Faculty> graduateAdvisors = new HashMap<>();
    private final Map<SchoolYear, Faculty> underGraduateAdvisors = new HashMap<>();

    protected final Registrar registrar = Registrar.getInstance();

    private final List<ConcentrationGroup> concentrationGroups = new ArrayList<>();
    private final List<Program> programs = new ArrayList<>();

    private final Set<Student> students = new HashSet<>();

    public Faculty getChairPerson() {
        return chairPerson;
    }

    public void setChairPerson(Faculty chairPerson) {
        logger.log(Level.INFO, "Setting {0} as Chairperson", chairPerson.getName());
        this.chairPerson = chairPerson;
        this.faculty.add(chairPerson);
    }

    public Collection<Faculty> getFaculty() {
        return faculty;
    }

    public Optional<Faculty> findFaculty(String name) {
        return getFaculty().stream().filter(f -> f.getName().equals(name)).findAny();
    }

    public void addFaculty(@NonNull Faculty faculty) {
        if (faculty.isFullTime())
            logger.log(Level.INFO, "Adding {0} as full-time faculty", faculty.getName());
        else
            logger.log(Level.INFO, "Adding {0} as part-time faculty", faculty.getName());

        this.faculty.add(faculty);
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

    public void addProgram(@NonNull Program program) {
        logger.log(Level.INFO, "Adding program {0}", program);
        getPrograms().add(program);
    }

    public List<ConcentrationGroup> getConcentrationGroups() {
        return concentrationGroups;
    }

    public List<ClassOffering> getClassOfferings() {
        return registrar.getClassOfferings();
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public Optional<Student> findStudent(String name) {
        return getStudents().stream().filter(f -> f.getName().equals(name)).findAny();
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
