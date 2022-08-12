package edu.bu.cs665.person;

/**
 *
 */
public class Advisor extends Title {
    public String description() {
        return "Advisor";
    }

    public static Title getInstance() {
        return new Advisor();
    }
}
