package edu.bu.cs665.hw03.entities.accounts;

import edu.bu.cs665.hw03.controllers.banks.BankOfAmerica;
import edu.bu.cs665.hw03.misc.Utilities;

import java.time.LocalDate;

/**
 * Bank of America Checking
 *
 * @author dlegaspi@bu.edu
 */
public class BankOfAmericaCheckingAccount extends CheckingAccount {
    public BankOfAmericaCheckingAccount(BankOfAmerica bank, String id, LocalDate enrollmentDate) {
        super(bank, id, enrollmentDate);
    }

    public BankOfAmericaCheckingAccount(BankOfAmerica bank) {
        super(bank);
    }

    public BankOfAmericaCheckingAccount(BankOfAmerica bank, String id) {
        super(bank, id);
    }

    @Override
    public String toString() {
        return String.format("%s::%s Checking Account", super.toString(),
                        Utilities.removeSpaces(this.getBank().getName()));
    }
}
