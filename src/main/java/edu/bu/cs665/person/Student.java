package edu.bu.cs665.person;

import edu.bu.cs665.EnrolledCourse;
import edu.bu.cs665.GpaComputeStrategy;
import edu.bu.cs665.program.Program;
import edu.bu.cs665.program.Thesis;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Student extends Person {
    public Student(String name) {
        super(name);
    }

    private GpaComputeStrategy gpaComputeStrategy;

    private Program program;
    private Thesis thesis;

    private final List<EnrolledCourse> enrolledCourses = new ArrayList<>();

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

    public double gpa() {
        return 0;
    }

    public GpaComputeStrategy getGpaComputeStrategy() {
        return gpaComputeStrategy;
    }

    public void setGpaComputeStrategy(GpaComputeStrategy gpaComputeStrategy) {
        this.gpaComputeStrategy = gpaComputeStrategy;
    }

    public List<EnrolledCourse> getEnrolledCourses() {
        return enrolledCourses;
    }
}
