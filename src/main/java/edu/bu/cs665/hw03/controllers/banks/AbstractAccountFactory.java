package edu.bu.cs665.hw03.controllers.banks;

import edu.bu.cs665.hw03.entities.accounts.CertificateOfDepositAccount;
import edu.bu.cs665.hw03.entities.accounts.CheckingAccount;
import edu.bu.cs665.hw03.entities.accounts.SavingsAccount;

/**
 * The interface for the Abstract Factory
 *
 * @author dlegaspi@bu.edu
 */
public interface AbstractAccountFactory {
    CheckingAccount createCheckingAccount();

    SavingsAccount createSavingsAccount();

    CertificateOfDepositAccount createCertificateOfDepositAccount();
}
