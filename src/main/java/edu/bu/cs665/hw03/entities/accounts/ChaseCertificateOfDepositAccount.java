package edu.bu.cs665.hw03.entities.accounts;

import edu.bu.cs665.hw03.controllers.banks.Chase;
import edu.bu.cs665.hw03.misc.Utilities;

import java.time.LocalDate;

/**
 * Chase CD
 *
 * @author dlegaspi@bu.edu
 */
public class ChaseCertificateOfDepositAccount extends CertificateOfDepositAccount {
    public ChaseCertificateOfDepositAccount(Chase bank, String id, LocalDate enrollmentDate) {
        super(bank, id, enrollmentDate);
    }

    public ChaseCertificateOfDepositAccount(Chase bank) {
        super(bank);
    }

    public ChaseCertificateOfDepositAccount(Chase bank, String id) {
        super(bank, id);
    }

    @Override
    public String toString() {
        return String.format("%s::%s Certificate of Deposit Account", super.toString(),
                        Utilities.removeSpaces(this.getBank().getName()));
    }
}
