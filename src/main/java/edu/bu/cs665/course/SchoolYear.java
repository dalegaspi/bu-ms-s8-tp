package edu.bu.cs665.course;

import edu.bu.cs665.AbstractEntity;

import java.util.List;

public class SchoolYear extends AbstractEntity {
    private final int year;

    private List<Semester> semesters;

    public SchoolYear(int year) {
        this.year = year;
    }

    public long getYear() {
        return year;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public static SchoolYear fromYear(int year) {
        return new SchoolYear(year);
    }

    @Override
    public String getId() {
        return String.format("SY-%d", year);
    }
}
