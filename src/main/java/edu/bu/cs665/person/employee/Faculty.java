package edu.bu.cs665.person.employee;

import edu.bu.cs665.person.Title;

public class Faculty extends Title {
    private boolean fullTime;

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

}
