package edu.bu.cs665;

import edu.bu.cs665.course.*;
import edu.bu.cs665.exceptions.InvalidClassOfferingState;
import edu.bu.cs665.exceptions.InvalidEnrollmentRequest;
import edu.bu.cs665.notifications.Event;
import edu.bu.cs665.notifications.Observer;
import edu.bu.cs665.notifications.Subject;
import edu.bu.cs665.person.Faculty;
import edu.bu.cs665.person.Person;
import edu.bu.cs665.person.Student;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller that deals with the validation of enrollments
 *
 * @author dlegaspi@bu.edu
 */
public final class Registrar implements Subject<Event> {
    private static final Logger logger = Logger.getLogger(Registrar.class.getName());

    public Registrar() {
        this.classOfferings = new ArrayList<>();
    }

    public static Registrar getInstance() {
        return new Registrar();
    }

    private Department department;

    private final List<ClassOffering> classOfferings;

    private final Map<ClassOffering, Set<Student>> classOfferingWaitList = new HashMap<>();

    public void addOffering(@NonNull final ClassOffering classOffering) {
        classOfferings.add(classOffering);
    }

    public Collection<ClassOffering> getClassOfferings() {
        return classOfferings;
    }

    public Optional<ClassOffering> findClassOffering(String courseId, Semester semester) {
        return getClassOfferings().stream()
                        .filter(co -> co.getCourse().getId().equals(courseId) && co.getSemester().equals(semester))
                        .findAny();
    }

    private void addToWaitList(ClassOffering classOffering, Student student) {
        if (!classOfferingWaitList.containsKey(classOffering)) {
            classOfferingWaitList.put(classOffering, new HashSet<>());
        }

        classOfferingWaitList.get(classOffering).add(student);
    }

    private void removeFromWaitList(ClassOffering classOffering, Student student) {
        assert classOfferings.contains(classOffering);

        classOfferingWaitList.get(classOffering).remove(student);
    }

    public ClassOffering createClassOffering(@NonNull Course course, @NonNull Faculty professor, Semester semester,
                    int enrollmentLimit) throws InvalidClassOfferingState {
        var classesForSemester = getClassOfferings().stream()
                        .filter(co -> co.getSemester().equals(semester) && co.getProfessor().equals(professor)).count();

        if (facultyExceedsClasses(professor, classesForSemester)) {
            throw new InvalidClassOfferingState("Professor exceeds courses to teach for specified semester");
        } else {
            var offering = new ClassOffering(course, professor, semester, enrollmentLimit);
            logger.log(Level.INFO, "Added course offering: {0}", offering);
            getClassOfferings().add(offering);

            professor.addClassOffering(offering);
            return offering;
        }
    }

    public final int PER_SEM_LIMIT_FULL_TIME_FACULTY = 3;
    public final int PER_SEM_LIMIT_PART_TIME_FACULTY = 1;

    private boolean facultyExceedsClasses(Faculty faculty, long count) {
        return faculty.isFullTime() ? count > PER_SEM_LIMIT_FULL_TIME_FACULTY : count > PER_SEM_LIMIT_PART_TIME_FACULTY;
    }

    public void processAddToWaitList(ClassOffering classOffering, Student student) {
        addToWaitList(classOffering, student);
        var subj = "Waitlist notification";
        var msg = String.format("You are waitlisted for Class %s", classOffering);
        var e = createEvent(subj, msg, student);
        notifyObservers(e);
    }

    public EnrolledCourse enrollCourse(@NonNull Student student, @NonNull ClassOffering classOffering)
                    throws InvalidEnrollmentRequest {
        // todo enrollment stuff ...including waitlist processing/notifications

        if (classOffering.getCourse() instanceof Elective && !student.isInFinalYear()) {
            throw new InvalidEnrollmentRequest("Cannot enroll elective course if not in final year of program");
        }

        if (classOffering.isFull()) {
            processAddToWaitList(classOffering, student);
            throw new InvalidEnrollmentRequest(
                            String.format("Class [%s] is full; [%s] is waitlisted", classOffering,
                                            student.getName()));
        }

        var ec = EnrolledCourse.createEnrolledCourse(classOffering.getCourse(), classOffering.getSemester(),
                        classOffering);

        classOffering.addStudent(student);
        student.addEnrolledCourse(ec);

        if (classOffering.isFull()) {
            // notify chairperson that class is full
            var e = new Event("Course Offering Full", String.format("Course offering %s is full", classOffering),
                            getDepartment().getChairPerson());
            notifyObservers(e);
        }

        return ec;
    }

    public EnrolledCourse dropCourse(@NonNull Student student, @NonNull EnrolledCourse enrolledCourse)
                    throws InvalidEnrollmentRequest {
        // todo drop course stuff ...including auto-enrollment
        return enrolledCourse;
    }

    private final List<Observer<Event>> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer<Event> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<Event> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Event event) {
        observers.forEach(observer -> observer.accept(event));
    }

    private Event createEvent(String subject, String message, Person recipient) {
        return new Event(subject, message, recipient);
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
