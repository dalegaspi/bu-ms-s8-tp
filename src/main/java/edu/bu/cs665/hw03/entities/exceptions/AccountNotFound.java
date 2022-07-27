package edu.bu.cs665.hw03.entities.exceptions;

public class AccountNotFound extends AccountException {
    public AccountNotFound() {
    }

    public AccountNotFound(String msg) {
        super(msg);
    }

    public AccountNotFound(String msg, Throwable t) {
        super(msg, t);
    }
}
