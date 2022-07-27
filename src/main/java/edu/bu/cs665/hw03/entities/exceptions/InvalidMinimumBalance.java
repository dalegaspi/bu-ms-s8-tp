package edu.bu.cs665.hw03.entities.exceptions;

import java.math.BigDecimal;

/**
 * Invalid min balance
 *
 * @author dlegaspi@bu.edu
 */
public class InvalidMinimumBalance extends BankException {

    public InvalidMinimumBalance(BigDecimal minimumBalance) {
        super("The minimum balance is " + minimumBalance);
    }

    public InvalidMinimumBalance(String msg) {
        super(msg);
    }

    public InvalidMinimumBalance(String msg, Throwable t) {
        super(msg, t);
    }
}
