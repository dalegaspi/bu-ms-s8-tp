package edu.bu.cs665.hw03.controllers.banks;

import edu.bu.cs665.hw03.controllers.TransactionCoordinator;
import edu.bu.cs665.hw03.entities.BankEntityCollection;
import edu.bu.cs665.hw03.entities.accounts.*;
import edu.bu.cs665.hw03.entities.customers.Address;
import edu.bu.cs665.hw03.entities.customers.AddressState;
import edu.bu.cs665.hw03.entities.customers.Customer;
import edu.bu.cs665.hw03.entities.exceptions.*;
import edu.bu.cs665.hw03.entities.transactions.AccountTransaction;
import edu.bu.cs665.hw03.entities.transactions.AccountTransactionType;
import edu.bu.cs665.hw03.misc.BankStatusView;
import edu.bu.cs665.hw03.misc.ConsoleBankStatusView;
import edu.bu.cs665.hw03.misc.Utilities;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static edu.bu.cs665.hw03.entities.transactions.AccountTransactionType.DEPOSIT;
import static edu.bu.cs665.hw03.entities.transactions.AccountTransactionType.WITHDRAWAL;
import static edu.bu.cs665.hw03.misc.Utilities.formatCurrency;
import static edu.bu.cs665.hw03.misc.Utilities.formatDate;

/**
 * Bank
 *
 * @author dlegaspi@bu.edu
 */
public class Bank implements AbstractAccountFactory {

    public String getName() {
        return "Basic";
    }

    @Override
    public String toString() {
        return getName();
    }

    public static BigDecimal ACCOUNT_MIN_BALANCE = BigDecimal.valueOf(100);
    private final BankEntityCollection<Customer> customers;
    private final BankEntityCollection<Account> accounts;
    private final TransactionCoordinator transactionCoordinator;

    public Bank(BankStatusView statusView) {
        customers = new BankEntityCollection<>();
        accounts = new BankEntityCollection<>();
        transactionCoordinator = new TransactionCoordinator();
        this.statusView = statusView;
    }

    public Bank() {
        statusView = new ConsoleBankStatusView();
        customers = new BankEntityCollection<>();
        accounts = new BankEntityCollection<>();
        transactionCoordinator = new TransactionCoordinator();
    }

    private final BankStatusView statusView;

    private static final Bank instance;
    static {
        instance = new Bank(new ConsoleBankStatusView());
    }

    public static Bank getInstance() {
        return instance;
    }

    public List<Customer> getCustomers() {
        return customers.asList();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void closeAccount(Account account) throws BankException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void renew(CertificateOfDepositAccount cd) throws BankException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Address createAddress(String street, String city, AddressState state, String zipCode) {
        return new Address(street, city, state, zipCode);
    }

    public Customer register(String id,
                    String name,
                    LocalDate registrationDate,
                    String phoneNumber,
                    Address address) {
        var customer = new Customer(this, id, name, registrationDate, phoneNumber, address);
        customers.add(customer);
        return customer;
    }

    public Customer register(String id, String name, LocalDate registrationDate) {
        return register(id, name, registrationDate, null, null);
    }

    private void addAccount(Account account) {
        accounts.add(account);
    }

    public CheckingAccount createCheckingAccount(@NonNull String id,
                    @NonNull LocalDate enrollmentDate) {
        return new CheckingAccount(this, id, enrollmentDate);
    }

    public SavingsAccount createSavingsAccount(@NonNull String id,
                    @NonNull LocalDate enrollmentDate) {
        return new SavingsAccount(this, id, enrollmentDate);
    }

    public Account setAccountOwner(@NonNull Account account,
                    @NonNull Customer owner) {

        account.setPrimaryOwner(owner);
        owner.addAccount(account);

        return account;
    }

    private Account setJointOwner(@NonNull Account account,
                    @NonNull Customer customer,
                    @NonNull LocalDate designationDate) {
        account.setJointOwner(customer, designationDate);
        return account;
    }

    public Account setJointOwner(@NonNull String accountId,
                    @NonNull String jointCustomerId,
                    @NonNull LocalDate designationDate) throws BankException {
        var account = findAccount(accountId);
        var customer = findCustomer(jointCustomerId);

        if (account.isEmpty()) {
            throw new AccountNotFound();
        }

        if (customer.isEmpty()) {
            throw new CustomerNotFound();
        }

        if (account.get().isPrimaryOwner(customer.get())) {
            throw new InvalidAccountState("Already the primary account holder");
        }

        return setJointOwner(account.get(), customer.get(), designationDate);
    }

    public boolean meetsMinimumEnrollmentAmount(BigDecimal amount) {
        return Utilities.isEqual(amount, ACCOUNT_MIN_BALANCE) || Utilities.isGreaterThan(amount, ACCOUNT_MIN_BALANCE);
    }

    private Account enroll(@NonNull Customer customer,
                    @NonNull Account account,
                    @NonNull LocalDate transactionDate,
                    @NonNull BigDecimal initialAmount) throws BankException {
        if (!meetsMinimumEnrollmentAmount(initialAmount))
            throw new InvalidMinimumBalance(initialAmount);
        makeCashTransaction(customer, account, transactionDate, DEPOSIT, initialAmount);
        setAccountOwner(account, customer);
        addAccount(account);
        return account;
    }

    public Account enrollCheckingAccount(@NonNull String customerId,
                    @NonNull String accountId,
                    @NonNull LocalDate enrollmentDate,
                    @NonNull BigDecimal initialAmount) throws BankException {
        var account = createCheckingAccount(accountId, enrollmentDate);
        var customer = findCustomer(customerId);

        if (customer.isEmpty()) {
            throw new CustomerNotFound();
        }

        enroll(customer.get(), account, enrollmentDate, initialAmount);

        return account;
    }

    public Account enrollSavingsAccount(@NonNull String customerId,
                    @NonNull String accountId,
                    @NonNull LocalDate enrollmentDate,
                    @NonNull BigDecimal initialAmount) throws BankException {
        var account = createSavingsAccount(accountId, enrollmentDate);
        var customer = findCustomer(customerId);

        if (customer.isEmpty()) {
            throw new CustomerNotFound();
        }

        enroll(customer.get(), account, enrollmentDate, initialAmount);
        return account;
    }

    public CertificateOfDepositAccount createCertificateOfDepositAccount(
                    @NonNull String accountId,
                    @NonNull final LocalDate enrollmentDate,
                    @NonNull final BigDecimal amount,
                    @NonNull final CertificateOfDepositTerm term) {
        var account = new CertificateOfDepositAccount(this, accountId, enrollmentDate);
        account.setTerm(term);
        return account;
    }

    public Optional<Account> findAccount(String id) {
        return accounts.findEntity(id);
    }

    public Optional<Customer> findCustomer(String id) {
        return customers.findEntity(id);
    }

    private AccountTransaction transferFunds(Customer initiator,
                    Account source,
                    Account destination,
                    LocalDate transactionDate,
                    BigDecimal amount) throws BankException {
        var transferStatus = String.format("\t%s  Transfer from %s to %s %s\tby %s", formatDate(transactionDate),
                        source.getId(),
                        destination.getId(), formatCurrency(amount), initiator.getName());
        statusView.println(transferStatus);

        // a transfer is technically a withdrawal from one account
        // and a deposit to another; we set a "related transaction"
        // to indicate that this is a transfer
        var withdrawal = makeCashTransaction(initiator, source, transactionDate, WITHDRAWAL, amount);
        if (!withdrawal.hasException()) {

            // we only continue the deposit if the withdrawal has actually succeeded
            var deposit = makeCashTransaction(initiator, destination, transactionDate, DEPOSIT, amount);
            deposit.setRelatedTransaction(withdrawal);
            withdrawal.setRelatedTransaction(deposit);

            // roll back the withdrawal in the event of an exception in deposit
            deposit.getException().ifPresent(e -> getTransactionCoordinator().rollback(e, withdrawal));
        }

        return withdrawal;
    }

    public AccountTransaction transferFunds(String customerId,
                    String accountSourceId,
                    String accountDestinationId,
                    LocalDate transactionDate,
                    BigDecimal amount) throws BankException {
        var customer = findCustomer(customerId);
        var accountSource = findAccount(accountSourceId);
        var accountDestination = findAccount(accountDestinationId);

        if (customer.isEmpty()) {
            throw new CustomerNotFound(customerId);
        }

        if (accountSource.isEmpty()) {
            throw new AccountNotFound(accountSourceId);
        }

        if (accountDestination.isEmpty()) {
            throw new AccountNotFound(accountDestinationId);
        }

        if (!accountSource.get().isOwner(customer.get()) || !accountDestination.get().isOwner(customer.get())) {
            throw new AccountNotOwner();
        }

        return transferFunds(customer.get(), accountSource.get(), accountDestination.get(), transactionDate, amount);
    }

    public TransactionCoordinator getTransactionCoordinator() {
        return transactionCoordinator;
    }

    private void addAmount(Account account, BigDecimal amount) {
        var result = account.getBalance().add(amount);
        account.setBalance(result);
    }

    private void subtractAmount(Account account, BigDecimal amount) {
        var result = account.getBalance().subtract(amount);
        account.setBalance(result);
    }

    private void applyAccountOperation(@NonNull AccountTransaction t,
                    @NonNull Account account,
                    @NonNull LocalDate transactionDate,
                    @NonNull BigDecimal amount) {

        try {
            // spotless:off
            switch (t.getType()) {
                case DEPOSIT -> addAmount(account, amount);
                case WITHDRAWAL -> {
                    if (Utilities.isGreaterThan(amount, account.getBalance())) {
                        throw new AccountCannotBeNegative();
                    } else {
                        subtractAmount(account, amount);
                    }
                }
            }
            // spotless:on
            t.setEndingBalance(account.getBalance());
            getTransactionCoordinator().commit(t);
        } catch (BankException e) {
            t.setException(e);
            getTransactionCoordinator().rollback(e, t);
        }

        account.addTransaction(t);
    }

    private AccountTransaction makeCashTransaction(@NonNull Customer customer,
                    @NonNull Account account,
                    LocalDate transactionDate,
                    AccountTransactionType transactionType,
                    BigDecimal amount) throws BankException {
        try {
            // this is where we lock an account and since the instance of account itself
            // is locked it allows thread-safe operation even if a withdrawal and
            // a deposit happens at the same time since and will also not require
            // a synchronized keyword on the method itself
            account.getLock().lock();
            var t = transactionCoordinator
                            .beginCashTransaction(this, customer, transactionDate, transactionType, account, amount);

            if (Utilities.isLessThan(amount, Utilities.toBankCurrency(0))) {
                throw new InvalidAccountState("Invalid amount");
            }

            applyAccountOperation(t, account, transactionDate, amount);
            statusView.println(t.toString());

            return t;
        } finally {
            // always unlock for a successful/failure operation
            account.getLock().unlock();
        }
    }

    public AccountTransaction makeCashWithdrawal(@NonNull String customerId,
                    @NonNull String accountId,
                    LocalDate transactionDate,
                    BigDecimal amount) throws BankException {
        var customer = findCustomer(customerId);
        var account = findAccount(accountId);

        if (customer.isEmpty()) {
            throw new CustomerNotFound(customerId);
        }

        if (account.isEmpty()) {
            throw new AccountNotFound(accountId);
        }

        if (!account.get().isOwner(customer.get())) {
            throw new AccountNotOwner();
        }

        return makeCashTransaction(customer.get(), account.get(), transactionDate, WITHDRAWAL, amount);
    }

    public AccountTransaction makeCashDeposit(@NonNull String customerId,
                    @NonNull String accountId,
                    LocalDate transactionDate,
                    BigDecimal amount) throws BankException {
        var customer = findCustomer(customerId);
        var account = findAccount(accountId);

        if (customer.isEmpty()) {
            throw new CustomerNotFound(customerId);
        }

        if (account.isEmpty()) {
            throw new AccountNotFound(accountId);
        }

        if (!account.get().isOwner(customer.get())) {
            throw new AccountNotOwner();
        }

        return makeCashTransaction(customer.get(), account.get(), transactionDate, DEPOSIT, amount);
    }

    public void printStatement(@NonNull String customerId, @NonNull LocalDate requestDate) throws BankException {
        var customer = findCustomer(customerId);

        if (customer.isEmpty()) {
            throw new CustomerNotFound(customerId);
        }

        statusView.println(String.format("\nBEGIN ACCOUNT STATEMENT - %s - %s", customer.get().getName(),
                        formatDate(requestDate)));

        customer.get().getAccounts().forEach(account -> {
            statusView.println(String.format("\n\tTransactions for Account %s Primary Owner: %s\n", account.getId(),
                            account.getPrimaryOwner().getName()));
            account.getTransactions().forEach(t -> statusView.println(t.toString()));

        });

        statusView.println("\nEND ACCOUNT STATEMENT");
    }

    public void printLineBreak() {
        statusView.println("");
    }

    public void print(String msg) {
        statusView.println(msg);
    }

    protected void createAccountTypeLogStatus() {

    }

    @Override
    public CheckingAccount createCheckingAccount() {
        return new CheckingAccount(this);
    }

    @Override
    public SavingsAccount createSavingsAccount() {
        return new SavingsAccount(this);
    }

    @Override
    public CertificateOfDepositAccount createCertificateOfDepositAccount() {
        return new CertificateOfDepositAccount(this);
    }
}
