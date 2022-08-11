package edu.bu.cs665.course;

import edu.bu.cs665.AbstractEntity;

public class Semester extends AbstractEntity {
    private final SchoolYear schoolYear;

    public Semester(SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }
}
