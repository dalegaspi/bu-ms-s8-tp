package edu.bu.cs665.person;

import edu.bu.cs665.course.EnrolledCourse;
import edu.bu.cs665.exceptions.InvalidEnrollmentState;
import edu.bu.cs665.grade.GpaComputeStrategy;
import edu.bu.cs665.program.DegreeProgram;
import edu.bu.cs665.program.Program;
import edu.bu.cs665.program.Thesis;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Student
 *
 * @author dlegaspi@bu.edu
 */
public class Student extends Person {
    private final static Logger logger = Logger.getLogger(Student.class.getName());

    public Student(String name) {
        super(name);
    }

    private GpaComputeStrategy gpaComputeStrategy;

    private Program program;
    private Thesis thesis;

    private final List<EnrolledCourse> enrolledCourses = new ArrayList<>();

    public void addEnrolledCourse(EnrolledCourse course) {
        enrolledCourses.add(course);
    }

    public boolean removeCourse(EnrolledCourse course) {
        return enrolledCourses.remove(course);
    }

    public Optional<EnrolledCourse> findEnrolledCourse(String courseId) {
        return getEnrolledCourses().stream().filter(ec -> ec.getCourse().getId().equals(courseId)).findAny();
    }

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
        return (getGpaComputeStrategy() != null) ? getGpaComputeStrategy().apply(getEnrolledCourses()) : 0;
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

    private String getEnrolledCoursesStatus() {
        String s = "Courses Taken: ";
        if (getEnrolledCourses().size() > 0) {
            return s + "\n" + getEnrolledCourses().stream()
                            .map(ec -> String.format("- Semester %s: %s\n", ec.getSemester(),
                                            ec.getCourse()))
                            .collect(Collectors.joining());
        } else {
            s += "None\n";
        }

        return s;
    }

    public String getFullStatus() {
        String s = String.format("Student: %s\n", getName());
        s += String.format("Program: %s\n", getProgram() != null ? getProgram() : "Not enrolled");
        s += getEnrolledCoursesStatus();
        s += String.format("Thesis: %s\n", getThesis().map(Thesis::getTitle).orElse("None"));
        s += String.format("Thesis Advisor: %s\n",
                        getThesis().flatMap(Thesis::getAdvisor).map(Faculty::getName).orElse("None"));
        s += String.format("GPA: %f", gpa());

        return s;
    }

    public void emitFullStatus() {
        logger.info("\n-----\n" + getFullStatus() + "\n-----");
    }

    public boolean isInFinalYear() {
        if (getProgram() == null) {
            throw new IllegalStateException("Student is not enrolled in any program");
        }

        var distinctYearsCount = getEnrolledCourses().stream().map(c -> c.getSemester().getSchoolYear()).distinct()
                        .count();

        return distinctYearsCount >= getProgram().minimumYearsToComplete();
    }
}
