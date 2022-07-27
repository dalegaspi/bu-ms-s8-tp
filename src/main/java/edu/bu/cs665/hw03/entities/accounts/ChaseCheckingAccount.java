package edu.bu.cs665.hw03.entities.accounts;

import edu.bu.cs665.hw03.controllers.banks.BankOfAmerica;
import edu.bu.cs665.hw03.controllers.banks.Chase;
import edu.bu.cs665.hw03.misc.Utilities;

import java.time.LocalDate;

/**
 * Chase Checking
 *
 * @author dlegaspi@bu.edu
 */
public class ChaseCheckingAccount extends CheckingAccount {
    public ChaseCheckingAccount(Chase bank, String id, LocalDate enrollmentDate) {
        super(bank, id, enrollmentDate);
    }

    public ChaseCheckingAccount(Chase bank) {
        super(bank);
    }

    public ChaseCheckingAccount(Chase bank, String id) {
        super(bank, id);
    }

    @Override
    public String toString() {
        return String.format("%s::%s Checking Account", super.toString(),
                        Utilities.removeSpaces(this.getBank().getName()));
    }
}
