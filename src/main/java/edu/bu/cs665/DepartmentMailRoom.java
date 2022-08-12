package edu.bu.cs665;

import edu.bu.cs665.exceptions.InvalidRecipientException;
import edu.bu.cs665.person.Faculty;
import edu.bu.cs665.person.Person;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Mailroom to handle sending of queries
 *
 * @author dlegaspi@bu.edu
 */
public final class DepartmentMailRoom {
    private static DepartmentMailRoom instance;

    private final Department department;

    public DepartmentMailRoom(Department department) {
        this.department = department;
    }

    private DepartmentMailRoom getInstance(Department department) {
        return new DepartmentMailRoom(department);
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
}
