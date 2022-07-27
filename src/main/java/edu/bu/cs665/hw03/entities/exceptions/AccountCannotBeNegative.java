package edu.bu.cs665.hw03.entities.exceptions;

/**
 * Account cannot be negative
 *
 * @author dlegaspi@bu.edu
 */
public class AccountCannotBeNegative extends InvalidAccountState {
    public AccountCannotBeNegative() {
        super("Not enough balance, withdrawal ignored");
    }

    public AccountCannotBeNegative(String msg) {
        super(msg);
    }

    public AccountCannotBeNegative(String msg, Throwable t) {
        super(msg, t);
    }
}
