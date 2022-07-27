package edu.bu.cs665.hw03.entities.transactions;

/**
 * Transaction type
 *
 * @author dlegaspi@bu.edu
 */
public enum AccountTransactionType {
    DEPOSIT,
    WITHDRAWAL;

    @Override
    public String toString() {
        return this.equals(DEPOSIT) ? "Deposit" : "Withdraw";
    }
}
