package edu.bu.cs665.hw03.views;

import edu.bu.cs665.hw03.controllers.banks.Bank;
import edu.bu.cs665.hw03.controllers.banks.Chase;

/**
 * Chase UI Builder
 *
 * @author dlegaspi@bu.edu
 */
public class ChaseUIBuilder extends BankUIBuilder {
    public ChaseUIBuilder(Bank bank) {
        super(bank);
    }

    public static ChaseUIBuilder getInstance() {
        return new ChaseUIBuilder(Chase.getInstance());
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
