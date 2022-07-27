package edu.bu.cs665.hw03.entities.accounts;

import edu.bu.cs665.hw03.controllers.banks.Bank;
import edu.bu.cs665.hw03.controllers.banks.BankOfAmerica;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Savings account
 *
 * @see Account
 * @see AccountWithInterest
 * @see ClosableAnytimeAccount
 * @author dlegaspi@bu.edu
 */
public class SavingsAccount extends Account implements AccountWithInterest, ClosableAnytimeAccount {
    public static final BigDecimal DEFAULT_INTEREST_RATE = BigDecimal.valueOf(0.02);

    private BigDecimal interestRate;

    public SavingsAccount(Bank bank, String id, LocalDate enrollmentDate) {
        super(bank, id, enrollmentDate);
    }

    public SavingsAccount(Bank bank) {
        super(bank);
    }

    public SavingsAccount(Bank bank, String id) {
        super(bank, id);
    }

    @Override
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "**Basic Savings Account**";
    }
}
