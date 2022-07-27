package edu.bu.cs665.hw03.controllers.banks;

import edu.bu.cs665.hw03.entities.accounts.BankOfAmericaCheckingAccount;
import edu.bu.cs665.hw03.entities.accounts.BankOfAmericaSavingsAccount;
import edu.bu.cs665.hw03.entities.accounts.CheckingAccount;
import edu.bu.cs665.hw03.entities.accounts.SavingsAccount;
import edu.bu.cs665.hw03.misc.ConsoleBankStatusView;

/**
 * Bank of America
 *
 * @author dlegaspi@bu.edu
 */
public class BankOfAmerica extends Bank {
    static Bank instance;
    static {
        instance = new BankOfAmerica(new ConsoleBankStatusView());
    }

    public BankOfAmerica(ConsoleBankStatusView consoleBankStatusView) {
    }

    public static Bank getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "Bank Of America";
    }

    @Override
    public CheckingAccount createCheckingAccount() {
        return new BankOfAmericaCheckingAccount(this);
    }

    @Override
    public SavingsAccount createSavingsAccount() {
        return new BankOfAmericaSavingsAccount(this);
    }
}
