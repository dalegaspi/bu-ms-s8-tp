package edu.bu.cs665.course;

import edu.bu.cs665.AbstractEntity;

import java.time.LocalDateTime;
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

    public static SchoolYear fromCurrentYear() {
        return fromYear(LocalDateTime.now().getYear());
    }

    @Override
    public String getId() {
        return String.format("SchoolYear-%d", year);
    }
}
