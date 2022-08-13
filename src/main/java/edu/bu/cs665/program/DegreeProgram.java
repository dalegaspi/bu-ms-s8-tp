package edu.bu.cs665.program;

public abstract class DegreeProgram extends Program {

    private int totalCoreCourses;
    private int totalElectiveCourses;

    public DegreeProgram(String title) {
        super(title);
    }

    public int getTotalCoreCourses() {
        return totalCoreCourses;
    }

    public void setTotalCoreCourses(int totalCoreCourses) {
        this.totalCoreCourses = totalCoreCourses;
    }

    public int getTotalElectiveCourses() {
        return totalElectiveCourses;
    }

    public void setTotalElectiveCourses(int totalElectiveCourses) {
        this.totalElectiveCourses = totalElectiveCourses;
    }
}
