package edu.bu.cs665.program;

public class UndergraduateProgram extends DegreeProgram {
    public UndergraduateProgram(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return String.format("Undergraduate Program %s", getTitle());
    }
}
