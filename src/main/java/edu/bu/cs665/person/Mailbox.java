package edu.bu.cs665.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Mailbox for receiving queries
 *
 * @author dlegaspi@bu.edu
 */
public class Mailbox {
    public record Message(Person sender, String subject, String message) {

        @Override
        public String toString() {
            return String.format("Message from [%s] Subject [%s] Message [%s]", sender.getName(), subject, message);
        }
    }

    private final List<Message> inbox = new ArrayList<>();

    public void addMessage(Person sender, String subject, String message) {
        inbox.add(new Message(sender, subject, message));
    }

    public List<Message> getMessages() {
        return this.inbox;
    }
}
