package edu.bu.cs665.hw03.entities.transactions;

import edu.bu.cs665.hw03.controllers.banks.Bank;
import edu.bu.cs665.hw03.entities.accounts.Account;
import edu.bu.cs665.hw03.entities.customers.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CheckTransaction extends AccountTransaction {
    private String checkId;

    public CheckTransaction(
                    Bank bank,
                    String id,
                    Customer initiator,
                    LocalDate transactionDate,
                    AccountTransactionType type,
                    Account account,
                    BigDecimal amount,
                    String checkId) {
        super(bank, id, initiator, transactionDate, type, account, amount);
        this.checkId = checkId;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }
}
