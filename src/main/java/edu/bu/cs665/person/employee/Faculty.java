package edu.bu.cs665.person.employee;

public class Faculty extends Title{
    private boolean partTime;

    public boolean isPartTime() {
        return partTime;
    }

    public void setPartTime(boolean partTime) {
        this.partTime = partTime;
    }
}
