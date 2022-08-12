package edu.bu.cs665.notifications;

import edu.bu.cs665.AbstractEntity;
import edu.bu.cs665.person.Person;

import java.util.HashMap;
import java.util.Map;

public class Event extends AbstractEntity {
    private String description;

    private final String subject;

    private final Person recipient;

    public Event(String subject, String message, Person recipient) {
        this.description = message;
        this.recipient = recipient;
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private final Map<String, String> properties = new HashMap<>();

    public Map<String, String> getProperties() {
        return properties;
    }

    public Person getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }
}
