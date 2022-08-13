package edu.bu.cs665;

import edu.bu.cs665.course.*;
import edu.bu.cs665.exceptions.InvalidClassOfferingState;
import edu.bu.cs665.exceptions.InvalidEnrollmentRequest;
import edu.bu.cs665.exceptions.InvalidRecipientException;
import edu.bu.cs665.grade.CourseGrade;
import edu.bu.cs665.grade.GpaComputeStrategy;
import edu.bu.cs665.messaging.DepartmentMailRoom;
import edu.bu.cs665.messaging.FacultyMessenger;
import edu.bu.cs665.person.Faculty;
import edu.bu.cs665.person.Person;
import edu.bu.cs665.person.Student;
import edu.bu.cs665.program.Program;
import edu.bu.cs665.program.Thesis;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static edu.bu.cs665.course.EnrolledCourse.totalCoursesGrades;
import static edu.bu.cs665.course.EnrolledCourse.totalCoursesUnits;

/**
 * Abstract class for university department
 *
 * @author dlegaspi@bu.edu
 */
public abstract class Department implements FacultyMessenger {

    public static final int DEFAULT_ENROLLMENT_LIMIT = 2;
    private static final Logger logger = Logger.getLogger(Department.class.getName());

    public abstract String getName();

    private final DepartmentMailRoom mailroom;

    public Department() {
        mailroom = DepartmentMailRoom.getInstance(this, registrar);
        registrar.setDepartment(this);
    }

    private Faculty chairPerson;
    private final Set<Faculty> faculty = new HashSet<>();
    private final Map<SchoolYear, Faculty> graduateAdvisors = new HashMap<>();
    private final Map<SchoolYear, Faculty> underGraduateAdvisors = new HashMap<>();

    private final Registrar registrar = Registrar.getInstance();

    public Registrar getRegistrar() {
        return this.registrar;
    }

    private final Set<Concentration> concentrations = new HashSet<>();

    private final Set<Course> courses = new HashSet<>();

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

    public Collection<Concentration> getConcentrations() {
        return concentrations;
    }

    public void addConcentration(Concentration c) {
        concentrations.add(c);
    }

    public Collection<ClassOffering> getClassOfferings() {
        return registrar.getClassOfferings();
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void enrollProgram(Student student, Program program) {
        getStudents().add(student);
        student.setProgram(program);
    }

    public void enrollProgram(Student student, String programTitle) throws InvalidEnrollmentRequest {
        var p = findProgram(programTitle)
                        .orElseThrow(() -> new InvalidEnrollmentRequest(
                                        String.format("Program %s not found", programTitle)));
        student.setProgram(p);
        student.setGpaComputeStrategy(getDefaultGpaComputeStrategy());
    }

    public Optional<Student> findStudent(String name) {
        return getStudents().stream().filter(f -> f.getName().equals(name)).findAny();
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Optional<Course> findCourse(String id) {
        return getCourses().stream().filter(c -> c.getId().equals(id)).findAny();
    }

    public void addThesis(@NonNull Student student, @NonNull Thesis thesis, @NonNull Semester semester, Faculty advisor)
                    throws InvalidEnrollmentRequest {

        if (!student.isInFinalYear())
            throw new InvalidEnrollmentRequest("Cannot assign thesis if student is not in final year of program");

        if (advisor != null) {
            if (!getFaculty().contains(advisor))
                throw new InvalidEnrollmentRequest("Requested thesis advisor is not member of faculty");

            if (!advisor.isFullTime())
                throw new InvalidEnrollmentRequest("Requested thesis advisor is not full-time member of faculty");
        }

        thesis.setAdvisor(advisor);
        student.setThesis(thesis);
    }

    public void assignGrade(Student student, String courseId, CourseGrade grade) {
        student.findEnrolledCourse(courseId).ifPresent(ec -> ec.setGrade(grade));
    }

    public interface DepartmentBuilder<T extends Department> {
        void addCoursesAndConcentrations();

        void addFaculty();

        void addPrograms();

        void addClassOfferings() throws InvalidClassOfferingState;

        T build();
    }

    public abstract <T extends Department> DepartmentBuilder<T> getBuilder();

    public void sendMessageToFaculty(@NonNull Person sender, @NonNull Faculty recipient, String subject, String message)
                    throws InvalidRecipientException {
        this.mailroom.sendMessageToFaculty(sender, recipient, subject, message);
    }

    public void sendMessageToChairPerson(@NonNull Person sender, String subject, String message)
                    throws InvalidRecipientException {
        this.mailroom.sendMessageToChairPerson(sender, subject, message);
    }

    private GpaComputeStrategy getDefaultGpaComputeStrategy() {
        // common GPA computation
        return enrolledCourses -> totalCoursesUnits(enrolledCourses) > 0
                        ? (double) totalCoursesGrades(enrolledCourses) / totalCoursesUnits(enrolledCourses)
                        : 0;
    }

    public Core createCoreCourse(String code, String title, String description) {
        var c = new Core(code, title, description);
        getCourses().add(c);

        return c;
    }

    public Elective createElectiveCourse(String code, String title, String description) {
        var c = new Elective(code, title, description);
        getCourses().add(c);

        return c;
    }
}
