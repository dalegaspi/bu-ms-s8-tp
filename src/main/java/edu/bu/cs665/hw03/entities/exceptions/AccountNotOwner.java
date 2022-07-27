package edu.bu.cs665.hw03.entities.exceptions;

public class AccountNotOwner extends AccountException {
    public AccountNotOwner() {
    }

    public AccountNotOwner(String msg) {
        super(msg);
    }

    public AccountNotOwner(String msg, Throwable t) {
        super(msg, t);
    }
}
