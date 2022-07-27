package edu.bu.cs665.hw03.entities.customers;

import edu.bu.cs665.hw03.controllers.banks.Bank;
import edu.bu.cs665.hw03.entities.BankEntity;
import edu.bu.cs665.hw03.entities.BankEntityCollection;
import edu.bu.cs665.hw03.entities.accounts.Account;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Customer
 *
 * @author dlegaspi@bu.edu
 */
public class Customer extends BankEntity {

    private String name;
    private String phoneNumber;
    private Address address;

    private BankEntityCollection<Account> accounts;

    private LocalDate registrationDate;

    public Customer() {

    }

    public Customer(@NonNull Bank bank,
                    @NonNull String id,
                    @NonNull String name,
                    @NonNull LocalDate registrationDate) {
        super(bank, id);
        this.registrationDate = registrationDate;
    }

    public Customer(@NonNull Bank bank,
                    @NonNull String id,
                    @NonNull String name,
                    @NonNull LocalDate registrationDate,
                    String phoneNumber,
                    Address address) {
        super(bank, id);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.registrationDate = registrationDate;
        this.accounts = new BankEntityCollection<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getPhoneNumber() {
        return Optional.ofNullable(phoneNumber);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Account> getAccounts() {
        return accounts.asList();
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Customer customer = (Customer) o;
        return getBank().equals(customer.getBank()) && getId().equals(customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBank(), getId());
    }

    @Override
    public String toString() {
        return String.format("**Customer**::%s",
                        this.getClass() == Customer.class ? "" : this.getClass().getSimpleName());
    }
}
