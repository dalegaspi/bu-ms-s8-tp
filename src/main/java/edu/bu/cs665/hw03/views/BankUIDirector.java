package edu.bu.cs665.hw03.views;

public class BankUIDirector {
    private static final BankUIDirector instance = new BankUIDirector();

    public static BankUIDirector getInstance() {
        return instance;
    }

    public void construct(BankUIBuilder builder) {
        builder.createHeader();
        builder.createSideBar();
        builder.createMainPanel();
        builder.createFooter();
    }
}
