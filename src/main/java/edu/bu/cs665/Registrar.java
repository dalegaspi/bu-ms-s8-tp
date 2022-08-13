package edu.bu.cs665;

import edu.bu.cs665.course.ClassOffering;
import edu.bu.cs665.course.Course;
import edu.bu.cs665.course.EnrolledCourse;
import edu.bu.cs665.course.Semester;
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

    public static final Registrar instance = new Registrar();

    public Registrar() {
        this.classOfferings = new ArrayList<>();
    }

    public static Registrar getInstance() {
        return instance;
    }

    private final List<ClassOffering> classOfferings;

    private final Map<ClassOffering, Set<Student>> classOfferingWaitList = new HashMap<>();

    public void addOffering(@NonNull final ClassOffering classOffering) {
        classOfferings.add(classOffering);
    }

    public List<ClassOffering> getClassOfferings() {
        return classOfferings;
    }

    private void addToWaitList(ClassOffering classOffering, Student student) {
        if (!classOfferingWaitList.containsKey(classOffering)) {
            classOfferingWaitList.put(classOffering, new HashSet<>());
        }

        classOfferingWaitList.get(classOffering).add(student);
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

            return offering;
        }
    }

    public final int PER_SEM_LIMIT_FULL_TIME_FACULTY = 3;
    public final int PER_SEM_LIMIT_PART_TIME_FACULTY = 1;

    private boolean facultyExceedsClasses(Faculty faculty, long count) {
        return faculty.isFullTime() ? count > PER_SEM_LIMIT_FULL_TIME_FACULTY : count > PER_SEM_LIMIT_PART_TIME_FACULTY;
    }

    public EnrolledCourse enrollCourse(@NonNull Student student, @NonNull ClassOffering classOffering)
                    throws InvalidEnrollmentRequest {
        // todo enrollment stuff ...including waitlist processing/notifications
        return null;
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
}
