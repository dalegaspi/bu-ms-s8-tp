package edu.bu.cs665.hw03;

import edu.bu.cs665.hw03.controllers.banks.Bank;
import edu.bu.cs665.hw03.entities.BankEntity;
import edu.bu.cs665.hw03.entities.accounts.Account;
import edu.bu.cs665.hw03.entities.customers.Customer;
import edu.bu.cs665.hw03.entities.customers.CustomerType;
import edu.bu.cs665.hw03.entities.customers.factories.simple.SimpleCustomerFactory;
import edu.bu.cs665.hw03.entities.exceptions.BankException;
import edu.bu.cs665.hw03.misc.Utilities;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static edu.bu.cs665.hw03.entities.BankEntity.generateId;
import static edu.bu.cs665.hw03.misc.Utilities.toBankCurrency;
import static edu.bu.cs665.hw03.misc.Utilities.toDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Utilities class test
 *
 * @author dlegaspi@bu.edu
 */
public class TransactionDateRangeTests {
    @Test
    public void testDateRange() {
        var d1 = LocalDate.of(2022, 8, 1);
        var d2 = LocalDate.of(2022, 12, 25);

        assertTrue(Utilities.inRange(d1, d1, d2, true));
        assertFalse(Utilities.inRange(d1, d1, d2, false));
        assertFalse(Utilities.inRange(LocalDate.EPOCH, d1, d2, true));
        assertTrue(Utilities.inRange(d2, d1, d2, true));
        assertFalse(Utilities.inRange(d2, d1, d2, false));
    }

    @Test
    public void testTransactionRange() throws BankException {
        var bank = Bank.getInstance();
        var customer = bank.register(generateId(), "John", toDate("2022-01-01"));
        var account = bank.enrollCheckingAccount(customer.getId(), generateId(), toDate("2022-01-01"), toBankCurrency(100));
        bank.makeCashDeposit(customer.getId(), account.getId(), toDate("2022-01-05"), toBankCurrency(100));
        bank.makeCashDeposit(customer.getId(), account.getId(), toDate("2022-01-07"), toBankCurrency(100));

        assertTrue(account.getTransactions().size() > 0);
        assertEquals(0, account.getTransactions(toDate("2000-01-01")).size());
        assertEquals(1, account.getTransactions(toDate("2022-01-01")).size());
        assertEquals(2, account.getTransactions(toDate("2022-01-01"), toDate("2022-01-05")).size());
        assertEquals(3, account.getTransactions(toDate("2022-01-10")).size());
        assertEquals(3, account.getTransactions(toDate("2022-01-10"), Duration.ofDays(14)).size());
    }
}
