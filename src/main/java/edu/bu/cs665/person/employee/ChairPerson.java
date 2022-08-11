package edu.bu.cs665.person.employee;

import edu.bu.cs665.person.Title;
import edu.bu.cs665.program.Concentration;

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
}
