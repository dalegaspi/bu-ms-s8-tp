package edu.bu.cs665.hw03.entities.accounts;

import edu.bu.cs665.hw03.controllers.banks.Bank;
import edu.bu.cs665.hw03.entities.BankEntity;
import edu.bu.cs665.hw03.entities.BankEntityCollection;
import edu.bu.cs665.hw03.entities.customers.Customer;
import edu.bu.cs665.hw03.entities.transactions.AccountTransaction;
import edu.bu.cs665.hw03.misc.Utilities;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Bank account
 *
 * @author dlegaspi@bu.edu
 */
public abstract class Account extends BankEntity {

    private final ReentrantLock lock;

    private Customer primaryOwner;
    private Customer jointOwner;

    private BigDecimal balance;

    private AccountStatus status;

    private LocalDate enrollmentDate;

    private LocalDate jointOwnerDate;

    private final BankEntityCollection<AccountTransaction> transactions;

    public Account(Bank bank, String id, LocalDate enrollmentDate) {
        super(bank, id);
        this.enrollmentDate = enrollmentDate;
        transactions = new BankEntityCollection<>();
        this.setBalance(Utilities.toBankCurrency(0));
        this.lock = new ReentrantLock();
    }

    public Account(Bank bank) {
        this(bank, BankEntity.generateId(), LocalDate.now());
    }

    public Account(Bank bank, String id) {
        this(bank, id, LocalDate.now());
    }

    public void addTransaction(AccountTransaction transaction) {
        transactions.add(transaction);
    }

    public List<AccountTransaction> getTransactions(LocalDate fromDate, LocalDate toDate, boolean inclusive) {
        return getTransactions().stream()
                        .filter(t -> Utilities.inRange(t.getTransactionDate(), fromDate, toDate, inclusive))
                        .toList();
    }

    public List<AccountTransaction> getTransactions(LocalDate fromDate, LocalDate toDate) {
        return getTransactions(fromDate, toDate, true);
    }

    public List<AccountTransaction> getTransactions(LocalDate toDate) {
        return getTransactions(toDate, true);
    }

    public List<AccountTransaction> getTransactions(LocalDate toDate, boolean inclusive) {
        return getTransactions(LocalDate.EPOCH, toDate, inclusive);
    }

    public List<AccountTransaction> getTransactions(LocalDate toDate, Duration duration, boolean inclusive) {
        return getTransactions(toDate.minus(duration), toDate, inclusive);
    }

    public List<AccountTransaction> getTransactions(LocalDate toDate, Duration duration) {
        return getTransactions(toDate.minusDays(duration.toDays()), toDate, true);
    }

    public List<AccountTransaction> getTransaction(Duration duration) {
        return getTransactions(LocalDate.now(), duration);
    }

    public List<AccountTransaction> getTransactions() {
        return transactions.asList();
    }

    public Optional<Customer> getJointOwner() {
        return Optional.ofNullable(jointOwner);
    }

    public void setJointOwner(@NonNull Customer jointOwner, @NonNull LocalDate designationDate) {
        this.jointOwner = jointOwner;
        this.jointOwner.getAccounts().add(this);
        this.jointOwnerDate = designationDate;
    }

    public Customer getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(Customer primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public void closeAccount() {
        this.status = AccountStatus.CLOSED;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void removeJointOwner() {
        getJointOwner().ifPresent(o -> o.getAccounts().remove(this));
        this.jointOwner = null;
        this.jointOwnerDate = null;
    }

    public boolean hasJointOwner() {
        return getJointOwner().isPresent();
    }

    public boolean isPrimaryOwner(@NonNull Customer customer) {
        return this.getPrimaryOwner().equals(customer);
    }

    public boolean isJointOwner(@NonNull Customer customer) {
        return this.getJointOwner().map(c -> c.equals(customer)).orElse(false);
    }

    public boolean isOwner(@NonNull Customer customer) {
        return isPrimaryOwner(customer) || isJointOwner(customer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Account account = (Account) o;
        return getBank().equals(account.getBank()) && getId().equals(account.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBank(), getId());
    }

    public ReentrantLock getLock() {
        return lock;
    }
}
