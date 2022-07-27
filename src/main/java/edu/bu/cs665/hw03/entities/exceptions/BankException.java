package edu.bu.cs665.hw03.entities.exceptions;

/**
 * Base exception
 *
 * @author dlegaspi@bu.edu
 */
public class BankException extends Exception {
    public BankException() {
        super();
    }

    public BankException(String msg) {
        super(msg);
    }

    public BankException(String msg, Throwable t) {
        super(msg, t);
    }
}
