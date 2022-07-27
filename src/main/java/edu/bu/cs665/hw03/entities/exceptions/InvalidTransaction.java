package edu.bu.cs665.hw03.entities.exceptions;

/**
 * Invalid transaction
 *
 * @author dlegaspi@bu.edu
 */
public class InvalidTransaction extends BankException {
    public InvalidTransaction() {
    }

    public InvalidTransaction(String msg) {
        super(msg);
    }

    public InvalidTransaction(String msg, Throwable t) {
        super(msg, t);
    }
}
