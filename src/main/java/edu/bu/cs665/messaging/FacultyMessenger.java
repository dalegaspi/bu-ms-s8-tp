package edu.bu.cs665.messaging;

import edu.bu.cs665.exceptions.InvalidRecipientException;
import edu.bu.cs665.person.Faculty;
import edu.bu.cs665.person.Person;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Faculty messenger to use the Proxy Pattern
 *
 * @author dlegaspi@bu.edu
 */
public interface FacultyMessenger {
    void sendMessageToFaculty(@NonNull Person sender, @NonNull Faculty recipient, String subject, String message)
                    throws InvalidRecipientException;

    void sendMessageToChairPerson(@NonNull Person sender, String subject, String message)
                    throws InvalidRecipientException;
}
