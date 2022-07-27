package edu.bu.cs665.hw03.entities.accounts;

import edu.bu.cs665.hw03.controllers.banks.Bank;
import edu.bu.cs665.hw03.controllers.banks.BankOfAmerica;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * CD
 *
 * @see Account
 * @see AccountWithInterest
 * @author dlegaspi@bu.edu
 */
public class CertificateOfDepositAccount extends Account implements AccountWithInterest {

    private CertificateOfDepositTerm term;

    private LocalDate termStart;

    public CertificateOfDepositAccount(Bank bank, String id, LocalDate enrollmentDate) {
        super(bank, id, enrollmentDate);
    }

    public CertificateOfDepositAccount(Bank bank) {
        super(bank);
    }

    public CertificateOfDepositAccount(Bank bank, String id) {
        super(bank, id);
    }

    @Override
    public BigDecimal getInterestRate() {
        return term.getInterestRate();
    }

    public void setTerm(CertificateOfDepositTerm term) {
        this.term = term;
    }

    public CertificateOfDepositTerm getTerm() {
        return this.term;
    }

    public LocalDate getTermStart() {
        return termStart;
    }

    public void setTermStart(LocalDate termStart) {
        this.termStart = termStart;
    }

    public LocalDate maturityDate() {
        return this.termStart.plusDays(this.term.getDuration().toDays());
    }

    @Override
    public String toString() {
        return "**Certificate of Deposit Account**";
    }
}
