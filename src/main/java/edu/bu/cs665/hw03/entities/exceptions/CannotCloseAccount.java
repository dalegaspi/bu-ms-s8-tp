package edu.bu.cs665.hw03.entities.exceptions;

/**
 * Cannot close account
 *
 * @author dlegaspi@bu.edu
 */
public class CannotCloseAccount extends InvalidAccountState {
    public CannotCloseAccount() {
    }

    public CannotCloseAccount(String msg) {
        super(msg);
    }

    public CannotCloseAccount(String msg, Throwable t) {
        super(msg, t);
    }
}
