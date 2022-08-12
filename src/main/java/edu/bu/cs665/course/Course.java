package edu.bu.cs665.course;

public class Course {
    public static final int UNITS = 4;

    public Course(String description, String syllabus, String title) {
        this.description = description;
        this.syllabus = syllabus;
        this.title = title;
    }

    public int getUnits() {
        return UNITS;
    }

    private final String description;

    private final String syllabus;

    private final String title;
}
