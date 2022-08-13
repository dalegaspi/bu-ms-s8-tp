package edu.bu.cs665.messaging;

import edu.bu.cs665.person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mailbox for receiving queries/notifications
 *
 * @author dlegaspi@bu.edu
 */
public final class Mailbox {
    private static final Logger logger = Logger.getLogger(Mailbox.class.getName());

    private final Person owner;

    public Mailbox(Person owner) {
        this.owner = owner;
    }

    public record Message(Person sender, String subject, String message) {

        @Override
        public String toString() {
            return String.format("Message from [%s] Subject [%s] Message [%s]", sender.getName(), subject, message);
        }
    }

    private final List<Message> inbox = new ArrayList<>();

    public void addMessage(Person sender, String subject, String message) {
        var msg = new Message(sender, subject, message);
        logger.log(Level.INFO, "Mailbox [{0}] receives message: {1}", new Object[] {
                        owner.getName(), msg });
        inbox.add(msg);
    }

    public List<Message> getMessages() {
        return this.inbox;
    }

    public boolean hasMessages() {
        return messageCount() > 0;
    }

    public int messageCount() {
        return getMessages().size();
    }
}
