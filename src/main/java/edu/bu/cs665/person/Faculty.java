package edu.bu.cs665.person;

import edu.bu.cs665.course.Course;
import edu.bu.cs665.course.Concentration;

import java.util.ArrayList;
import java.util.List;

public class Faculty extends Employee {
    private boolean fullTime;

    public Faculty(String name) {
        super(name);
    }

    public boolean isFullTime() {
        return fullTime && !isChairPerson();
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    private List<Concentration> coordinatedConcentrations = new ArrayList<>();
    private List<Course> coursesTaught;

    public List<Concentration> getCoordinatedConcentrations() {
        return coordinatedConcentrations;
    }

    public void setCoordinatedConcentrations(List<Concentration> coordinatedConcentrations) {
        this.coordinatedConcentrations = coordinatedConcentrations;
    }
}
