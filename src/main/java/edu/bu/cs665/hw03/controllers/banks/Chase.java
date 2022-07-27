package edu.bu.cs665.hw03.controllers.banks;

import edu.bu.cs665.hw03.entities.accounts.*;
import edu.bu.cs665.hw03.misc.ConsoleBankStatusView;

/**
 * Chase
 *
 * @author dlegaspi@bu.edu
 */
public class Chase extends Bank {
    static Bank instance;
    static {
        instance = new Chase(new ConsoleBankStatusView());
    }

    public Chase(ConsoleBankStatusView consoleBankStatusView) {
    }

    public static Bank getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "Chase";
    }

    @Override
    public CheckingAccount createCheckingAccount() {
        return new ChaseCheckingAccount(this);
    }

    @Override
    public SavingsAccount createSavingsAccount() {
        return new ChaseSavingsAccount(this);
    }
}
