package edu.bu.cs665.hw03.entities;

import edu.bu.cs665.hw03.controllers.banks.Bank;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Objects;
import java.util.UUID;

public class BankEntity {
    private Bank bank;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public BankEntity() {
        this.id = generateId();
    }

    public BankEntity(@NonNull Bank bank, @NonNull String id) {
        this.id = id;
        this.bank = bank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BankEntity that = (BankEntity) o;
        return getBank().equals(that.getBank()) && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBank(), getId());
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
