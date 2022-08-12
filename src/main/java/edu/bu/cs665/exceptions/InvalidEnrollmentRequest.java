package edu.bu.cs665.exceptions;

public class InvalidEnrollmentRequest extends SchoolException {
    public InvalidEnrollmentRequest() {
    }

    public InvalidEnrollmentRequest(String msg) {
        super(msg);
    }

    public InvalidEnrollmentRequest(String msg, Throwable t) {
        super(msg, t);
    }
}
