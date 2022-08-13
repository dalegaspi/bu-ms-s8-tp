package edu.bu.cs665;

import edu.bu.cs665.course.ConcentrationGroup;
import edu.bu.cs665.course.SchoolYear;
import edu.bu.cs665.exceptions.InvalidEnrollmentRequest;
import edu.bu.cs665.exceptions.InvalidRecipientException;
import edu.bu.cs665.messaging.FacultyMessenger;
import edu.bu.cs665.person.Faculty;
import edu.bu.cs665.person.Person;
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
public abstract class Department implements FacultyMessenger {

    public void sendMessageToFaculty(@NonNull Person sender, @NonNull Faculty recipient, String subject, String message)
                    throws InvalidRecipientException {
        this.mailroom.sendMessageToFaculty(sender, recipient, subject, message);
    }

    public void sendMessageToChairPerson(@NonNull Person sender, String subject, String message)
                    throws InvalidRecipientException {
        this.mailroom.sendMessageToChairPerson(sender, subject, message);
    }

    private static final Logger logger = Logger.getLogger(Department.class.getName());

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
    private final Set<Program> programs = new HashSet<>();

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

    public Collection<Program> getPrograms() {
        return programs;
    }

    public Optional<Program> findProgram(String title) {
        return getPrograms().stream().filter(p -> p.getTitle().equals(title)).findAny();
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

    public void enrollProgram(Student student, Program program) {
        assert getStudents().contains(student);

        getStudents().add(student);
        student.setProgram(program);
    }

    public void enrollProgram(Student student, String programTitle) throws InvalidEnrollmentRequest {
        assert getStudents().contains(student);

        findProgram(programTitle).map(p -> {
            student.setProgram(p);
            return true;
        }).orElseThrow(() -> new InvalidEnrollmentRequest(String.format("Program %s not found", programTitle)));
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
