package edu.bu.cs665.person.employee;

import edu.bu.cs665.course.Course;
import edu.bu.cs665.person.Title;
import edu.bu.cs665.program.Concentration;

import java.util.ArrayList;
import java.util.List;

public class Faculty extends Title {
    private boolean fullTime;

    public boolean isFullTime() {
        return fullTime;
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

    public String description() {
        return isFullTime() ? "Full-Time" : "Part-Time" + " Faculty";
    }

    public static Title getInstance(boolean fullTime) {
        var f = new Faculty();
        f.setFullTime(fullTime);
        return f;
    }
}
