package edu.bu.cs665.person;

import edu.bu.cs665.program.Program;
import edu.bu.cs665.program.Thesis;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Optional;

public class Student extends Person {
    public Student(String name) {
        super(name);
    }

    private Program program;
    private Thesis thesis;


    public Optional<Thesis> getThesis() {
        return Optional.ofNullable(thesis);
    }

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(@NonNull Program program) {
        this.program = program;
    }
}
