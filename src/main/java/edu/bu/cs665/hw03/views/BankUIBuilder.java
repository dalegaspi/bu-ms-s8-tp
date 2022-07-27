package edu.bu.cs665.hw03.views;

import edu.bu.cs665.hw03.controllers.banks.Bank;

/**
 * Builder for UI
 *
 * @author dlegaspi@bu.edu
 */
public abstract class BankUIBuilder {
    public BankUIBuilder(Bank bank) {
        this.bank = bank;
        ui = new BankUI();
    }

    protected BankUI ui;

    protected Bank bank;

    public Bank getBank() {
        return this.bank;
    }

    protected void addPart(String part) {
        ui.add(String.format("%s %s", this.getBank(), part));
    }

    public abstract void createHeader();

    public abstract void createSideBar();

    public abstract void createMainPanel();

    public abstract void createFooter();

    public BankUI getUI() {
        return ui;
    }
}
