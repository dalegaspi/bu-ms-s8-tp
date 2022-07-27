package edu.bu.cs665.hw03.entities.transactions;

import edu.bu.cs665.hw03.controllers.banks.Bank;
import edu.bu.cs665.hw03.entities.accounts.Account;
import edu.bu.cs665.hw03.entities.customers.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Cash transactions
 *
 * @author dlegaspi@bu.edu
 * @see AccountTransaction
 */
public class CashTransaction extends AccountTransaction {
    public CashTransaction(Bank bank,
                    String id,
                    Customer owner,
                    LocalDate transactionDate,
                    AccountTransactionType type,
                    Account account,
                    BigDecimal amount) {
        super(bank, id, owner, transactionDate, type, account, amount);
    }
}
