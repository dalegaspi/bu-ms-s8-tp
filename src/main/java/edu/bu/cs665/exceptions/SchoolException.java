package edu.bu.cs665.exceptions;

public class SchoolException extends Exception {
    public SchoolException() {
        super();
    }

    public SchoolException(String msg) {
        super(msg);
    }

    public SchoolException(String msg, Throwable t) {
        super(msg, t);
    }
}
