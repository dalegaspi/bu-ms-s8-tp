package edu.bu.cs665.hw03.entities.accounts;

import edu.bu.cs665.hw03.controllers.banks.Bank;
import edu.bu.cs665.hw03.controllers.banks.BankOfAmerica;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Checking account
 *
 * @see Account
 * @see ClosableAnytimeAccount
 * @author dlegaspi@bu.edu
 */
public class CheckingAccount extends Account implements ClosableAnytimeAccount {
    public static final BigDecimal CHECK_FEE = BigDecimal.valueOf(0.50);
    public static final int FREE_CHECKS_PER_MONTH = 10;

    public CheckingAccount(Bank bank, String id, LocalDate enrollmentDate) {
        super(bank, id, enrollmentDate);
    }

    public CheckingAccount(Bank bank) {
        super(bank);
    }

    public CheckingAccount(Bank bank, String id) {
        super(bank, id);
    }

    @Override
    public String toString() {
        return "**Basic Checking Account**";
    }
}
