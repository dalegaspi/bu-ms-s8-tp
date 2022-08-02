package edu.bu.cs665.course;

import edu.bu.cs665.program.Concentration;

import java.util.List;

public class Course {
    private List<Concentration> concentrations;

    public List<Concentration> getConcentrations() {
        return concentrations;
    }

    public void setConcentrations(List<Concentration> concentrations) {
        this.concentrations = concentrations;
    }
}
