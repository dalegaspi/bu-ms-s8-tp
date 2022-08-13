package edu.bu.cs665.course;

import edu.bu.cs665.entity.AbstractEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SchoolYear extends AbstractEntity {
    private final int year;

    private List<Semester> semesters = new ArrayList<>();

    public SchoolYear(int year) {
        this.year = year;
        this.semesters.add(new Semester(this, 1));
        this.semesters.add(new Semester(this, 2));
    }

    public long getYear() {
        return year;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public Semester getSemester(int semester) {
        assert semester > 0 && semester <= semesters.size();

        return getSemesters().get(semester - 1);
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

    @Override
    public String toString() {
        return String.valueOf(year);
    }
}
