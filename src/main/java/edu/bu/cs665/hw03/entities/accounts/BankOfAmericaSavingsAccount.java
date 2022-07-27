package edu.bu.cs665.hw03.entities.accounts;

import edu.bu.cs665.hw03.controllers.banks.BankOfAmerica;
import edu.bu.cs665.hw03.misc.Utilities;

import java.time.LocalDate;

/**
 * Bank of America Savings
 *
 * @author dlegaspi@bu.edu
 */
public class BankOfAmericaSavingsAccount extends SavingsAccount {
    public BankOfAmericaSavingsAccount(BankOfAmerica bank, String id, LocalDate enrollmentDate) {
        super(bank, id, enrollmentDate);
    }

    public BankOfAmericaSavingsAccount(BankOfAmerica bank) {
        super(bank);
    }

    public BankOfAmericaSavingsAccount(BankOfAmerica bank, String id) {
        super(bank, id);
    }

    @Override
    public String toString() {
        return String.format("%s::%s Savings Account", super.toString(),
                        Utilities.removeSpaces(this.getBank().getName()));
    }
}
