package edu.bu.cs665.exceptions;

public class InvalidRecipientException extends SchoolException {
    public InvalidRecipientException() {
    }

    public InvalidRecipientException(String msg) {
        super(msg);
    }

    public InvalidRecipientException(String msg, Throwable t) {
        super(msg, t);
    }
}
