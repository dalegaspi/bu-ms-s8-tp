package edu.bu.cs665.program;

public class UndergraduateProgram extends DegreeProgram {
    @Override
    public int minimumYearsToComplete() {
        return 4;
    }

    public UndergraduateProgram(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return String.format("Undergraduate Program %s", getTitle());
    }
}
