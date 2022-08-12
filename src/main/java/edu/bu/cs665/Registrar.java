package edu.bu.cs665;

import edu.bu.cs665.notifications.Event;
import edu.bu.cs665.notifications.Observer;
import edu.bu.cs665.notifications.Subject;
import edu.bu.cs665.person.Person;
import edu.bu.cs665.person.Student;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller that deals with the validation of enrollments
 *
 * @author dlegaspi@bu.edu
 */
public final class Registrar implements Subject<Event> {

    public static final Registrar instance = new Registrar();

    public Registrar() {
        this.classOfferings = new ArrayList<>();
    }

    public static Registrar getInstance() {
        return instance;
    }

    private final List<ClassOffering> classOfferings;

    private final Map<ClassOffering, List<Student>> classOfferingWaitList = new HashMap<>();

    public void addOffering(@NonNull final ClassOffering classOffering) {
        classOfferings.add(classOffering);
    }

    public List<ClassOffering> getClassOfferings() {
        return classOfferings;
    }

    private addToWaitList(ClassOffering classOffering, Student student)

    public EnrollmentStatus enroll(@NonNull Student student, @NonNull ClassOffering classOffering) {
        // todo enrollment stuff
        return EnrollmentStatus.SUCCESS;
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

    private Event createEvent(String message, Person recipient) {
        return new Event(message, recipient);
    }
}
