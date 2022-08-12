package edu.bu.cs665.program;

public class GraduateProgram extends DegreeProgram {
    public GraduateProgram(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return String.format("Graduate Program %s", getTitle());
    }
}
