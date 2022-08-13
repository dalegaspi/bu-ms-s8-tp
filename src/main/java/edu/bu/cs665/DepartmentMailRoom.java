package edu.bu.cs665;

import edu.bu.cs665.exceptions.InvalidRecipientException;
import edu.bu.cs665.messaging.FacultyMessenger;
import edu.bu.cs665.notifications.Event;
import edu.bu.cs665.notifications.Observer;
import edu.bu.cs665.person.Faculty;
import edu.bu.cs665.person.Person;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Mailroom to handle sending of queries
 *
 * @author dlegaspi@bu.edu
 */
public final class DepartmentMailRoom implements Observer<Event>, FacultyMessenger {
    private final Department department;

    public DepartmentMailRoom(Department department, Registrar registrar) {
        this.department = department;
        registrar.registerObserver(this);
    }

    public static DepartmentMailRoom getInstance(Department department, Registrar registrar) {
        return new DepartmentMailRoom(department, registrar);
    }

    public void sendMessageToChairPerson(@NonNull Person sender, String subject, String message)
                    throws InvalidRecipientException {
        sendMessageToFaculty(sender, department.getChairPerson(), subject, message);
    }

    public void sendMessageToFaculty(@NonNull Person sender, @NonNull Faculty recipient, String subject, String message)
                    throws InvalidRecipientException {
        if (department.getFaculty().contains(recipient)) {
            throw new InvalidRecipientException(String.format("%s is not from this department", recipient.getName()));
        }

        sendMessage(sender, recipient, subject, message);
    }

    public void sendMessage(@NonNull Person sender, @NonNull Person recipient, String subject, String message) {
        recipient.getMailbox().addMessage(sender, subject, message);
    }

    private final Person mailroomSender = Person.createEmployee("Mailroom");

    @Override
    public void accept(Event event) {
        if (event.getRecipient() instanceof Faculty) {
            sendMessage(mailroomSender, event.getRecipient(), event.getSubject(), event.getDescription());
        }
    }
}
