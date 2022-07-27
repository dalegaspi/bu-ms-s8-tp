package edu.bu.cs665.hw03.entities.transactions;

import edu.bu.cs665.hw03.controllers.banks.Bank;
import edu.bu.cs665.hw03.entities.BankEntity;
import edu.bu.cs665.hw03.entities.accounts.Account;
import edu.bu.cs665.hw03.entities.customers.Customer;
import edu.bu.cs665.hw03.entities.exceptions.BankException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static edu.bu.cs665.hw03.misc.Utilities.formatCurrency;
import static edu.bu.cs665.hw03.misc.Utilities.formatDate;

/**
 * Account transaction base class
 *
 * @author dlegaspi@bu.edu
 */
public abstract class AccountTransaction extends BankEntity {
    private final LocalDate transactionDate;

    private final Account account;
    private final BigDecimal amount;
    private BigDecimal endingBalance;

    private final AccountTransactionType type;

    private AccountTransactionStatus status;

    private AccountTransaction relatedTransaction;

    private Customer initiator;

    private BankException exception;

    private final BigDecimal originalBalance;

    protected AccountTransaction(
                    Bank bank,
                    String id,
                    Customer initiator,
                    LocalDate transactionDate,
                    AccountTransactionType type,
                    Account account,
                    BigDecimal amount) {
        super(bank, id);
        this.transactionDate = transactionDate;
        this.account = account;
        this.amount = amount;
        this.type = type;
        this.status = AccountTransactionStatus.CREATED;
        this.initiator = initiator;
        this.originalBalance = account.getBalance();
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public Account getAccount() {
        return account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getEndingBalance() {
        return endingBalance;
    }

    public AccountTransactionType getType() {
        return type;
    }

    public AccountTransactionStatus getStatus() {
        return status;
    }

    public void setStatus(AccountTransactionStatus status) {
        this.status = status;
    }

    public boolean isInProgress() {
        return this.status == AccountTransactionStatus.IN_PROGRESS;
    }

    public boolean isCompleted() {
        return this.status == AccountTransactionStatus.COMMITTED || this.status == AccountTransactionStatus.ROLLED_BACK;
    }

    public void setEndingBalance(BigDecimal endingBalance) {
        this.endingBalance = endingBalance;
    }

    public Optional<AccountTransaction> getRelatedTransaction() {
        return Optional.ofNullable(relatedTransaction);
    }

    public void setRelatedTransaction(AccountTransaction relatedTransaction) {
        this.relatedTransaction = relatedTransaction;
    }

    public Customer getInitiator() {
        return initiator;
    }

    public Optional<BankException> getException() {
        return Optional.ofNullable(exception);
    }

    public void setException(BankException exception) {
        this.exception = exception;
    }

    public boolean hasException() {
        return getException().isPresent();
    }

    @Override
    public String toString() {
        var formattedTransaction = getRelatedTransaction().map(t -> {
            String srcAccountId;
            String dstAccountId;
            if (getType().equals(AccountTransactionType.WITHDRAWAL)) {
                dstAccountId = t.getAccount().getId();
                srcAccountId = getAccount().getId();
            } else {
                srcAccountId = t.getAccount().getId();
                dstAccountId = getAccount().getId();
            }

            return String.format("\t%s  Transfer from %s to %s %s\t\tby %s\n",
                            formatDate(getTransactionDate()),
                            srcAccountId,
                            dstAccountId,
                            formatCurrency(getAmount()),
                            getInitiator().getName());
        }).orElse("");
        return formattedTransaction + getFormattedTransaction(this);
    }

    protected static String getFormattedTransaction(AccountTransaction t) {
        var transactionTypeFormatted = t.getException().map(Exception::getMessage).orElse(t.getType().toString());
        return String.format("\t%s  Account: %s\t\t%s %s\t\tby %s\t\tRunning Balance %s",
                        formatDate(t.getTransactionDate()),
                        t.getAccount().getId(),
                        transactionTypeFormatted,
                        formatCurrency(t.getAmount()),
                        t.getInitiator().getName(),
                        formatCurrency(t.getEndingBalance()));
    }

    public BigDecimal getOriginalBalance() {
        return originalBalance;
    }
}
