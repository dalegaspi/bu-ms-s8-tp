package edu.bu.cs665.hw03.entities.exceptions;

public class CustomerNotFound extends BankException {
    public CustomerNotFound() {
    }

    public CustomerNotFound(String msg) {
        super(msg);
    }

    public CustomerNotFound(String msg, Throwable t) {
        super(msg, t);
    }
}
