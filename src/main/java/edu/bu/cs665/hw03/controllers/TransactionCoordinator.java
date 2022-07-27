package edu.bu.cs665.hw03.controllers;

import edu.bu.cs665.hw03.controllers.banks.Bank;
import edu.bu.cs665.hw03.entities.accounts.Account;
import edu.bu.cs665.hw03.entities.customers.Customer;
import edu.bu.cs665.hw03.entities.exceptions.BankException;
import edu.bu.cs665.hw03.entities.transactions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Transaction coordinator
 *
 * @see AccountTransaction
 * @see CashTransaction
 * @see CheckTransaction
 * @see Account
 * @author dlegaspi@bu.edu
 */
public class TransactionCoordinator {

    private String generateTransactionId() {
        return UUID.randomUUID().toString();
    }

    public CheckTransaction beginCheckTransaction(Bank bank,
                    Customer initiator,
                    LocalDate transactionDate,
                    AccountTransactionType type,
                    Account account,
                    BigDecimal amount,
                    String checkId) {
        var t = new CheckTransaction(bank, generateTransactionId(), initiator, transactionDate, type, account, amount,
                        checkId);
        t.setStatus(AccountTransactionStatus.IN_PROGRESS);
        return t;
    }

    public CashTransaction beginCashTransaction(Bank bank,
                    Customer initiator,
                    LocalDate transactionDate,
                    AccountTransactionType type,
                    Account account,
                    BigDecimal amount) {
        var t = new CashTransaction(bank, generateTransactionId(), initiator, transactionDate, type, account, amount);
        t.setStatus(AccountTransactionStatus.IN_PROGRESS);
        return t;
    }

    public void rollback(
                    BankException exception,
                    AccountTransaction transaction) {
        transaction.setException(exception);
        transaction.setEndingBalance(transaction.getOriginalBalance());
        transaction.setStatus(AccountTransactionStatus.ROLLED_BACK);
    }

    public void commit(

                    AccountTransaction transaction) {

        transaction.setStatus(AccountTransactionStatus.COMMITTED);
    }
}
