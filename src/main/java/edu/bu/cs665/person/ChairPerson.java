package edu.bu.cs665.person;

import edu.bu.cs665.course.Concentration;

import java.util.ArrayList;
import java.util.List;

public class ChairPerson extends Title {
    private List<Concentration> topLevelConcentrations = new ArrayList<>();

    public List<Concentration> getTopLevelConcentrations() {
        return topLevelConcentrations;
    }

    public void setTopLevelConcentrations(List<Concentration> topLevelConcentrations) {
        this.topLevelConcentrations = topLevelConcentrations;
    }

    public String description() {
        return "ChairPerson";
    }

    public static Title getInstance() {
        return new ChairPerson();
    }
}
