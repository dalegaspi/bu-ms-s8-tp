package edu.bu.cs665.hw03.entities.exceptions;

/**
 * Invalid account state
 *
 * @author dlegaspi@bu.edu
 */
public class InvalidAccountState extends AccountException {
    public InvalidAccountState() {
    }

    public InvalidAccountState(String msg) {
        super(msg);
    }

    public InvalidAccountState(String msg, Throwable t) {
        super(msg, t);
    }
}
