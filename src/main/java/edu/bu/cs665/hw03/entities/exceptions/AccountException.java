package edu.bu.cs665.hw03.entities.exceptions;

public class AccountException extends BankException {
    public AccountException() {
    }

    public AccountException(String msg) {
        super(msg);
    }

    public AccountException(String msg, Throwable t) {
        super(msg, t);
    }
}
