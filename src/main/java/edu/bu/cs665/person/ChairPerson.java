package edu.bu.cs665.person;

public class ChairPerson extends Title {

    public String description() {
        return "ChairPerson";
    }

    public static Title getInstance() {
        return new ChairPerson();
    }
}
