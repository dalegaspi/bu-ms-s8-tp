package edu.bu.cs665.hw03.entities.accounts;

import edu.bu.cs665.hw03.controllers.banks.BankOfAmerica;
import edu.bu.cs665.hw03.misc.Utilities;

import java.time.LocalDate;

/**
 * Bank of America CD
 *
 * @author dlegaspi@bu.edu
 */
public class BankOfAmericaCertificateOfDepositAccount extends CertificateOfDepositAccount {
    public BankOfAmericaCertificateOfDepositAccount(BankOfAmerica bank, String id, LocalDate enrollmentDate) {
        super(bank, id, enrollmentDate);
    }

    public BankOfAmericaCertificateOfDepositAccount(BankOfAmerica bank) {
        super(bank);
    }

    public BankOfAmericaCertificateOfDepositAccount(BankOfAmerica bank, String id) {
        super(bank, id);
    }

    @Override
    public String toString() {
        return String.format("%s::%s Certificate of Deposit Account", super.toString(),
                        Utilities.removeSpaces(this.getBank().getName()));
    }
}
