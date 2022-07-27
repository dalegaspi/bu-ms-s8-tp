package edu.bu.cs665.hw03.views;

import edu.bu.cs665.hw03.controllers.banks.Bank;
import edu.bu.cs665.hw03.controllers.banks.BankOfAmerica;

/**
 * Bank of America UI builder
 *
 * @author dlegaspi@bu.edu
 */
public class BankOfAmericaUIBuilder extends BankUIBuilder {
    private Bank bank;

    public BankOfAmericaUIBuilder(Bank bank) {
        super(bank);
    }

    public static BankOfAmericaUIBuilder getInstance() {
        return new BankOfAmericaUIBuilder(BankOfAmerica.getInstance());
    }

    @Override
    public void createHeader() {
        addPart("Header");
    }

    @Override
    public void createSideBar() {
        addPart("Sidebar");

    }

    @Override
    public void createMainPanel() {
        addPart("Main panel");
    }

    @Override
    public void createFooter() {
        addPart("Footer");
    }
}
