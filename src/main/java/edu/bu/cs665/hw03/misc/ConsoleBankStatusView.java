package edu.bu.cs665.hw03.misc;

public class ConsoleBankStatusView implements BankStatusView {
    @Override
    public void println(String msg) {
        System.out.println(msg);
    }

    @Override
    public void print(String msg) {
        System.out.print(msg);
    }
}
