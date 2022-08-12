package edu.bu.cs665.course;

import edu.bu.cs665.AbstractEntity;

public class Semester extends AbstractEntity {
    private final SchoolYear schoolYear;
    private final int semester;

    public Semester(SchoolYear schoolYear, int semester) {
        this.schoolYear = schoolYear;
        this.semester = semester;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    @Override
    public String getId() {
        return String.format("Semester-%d", getSemester(), getSchoolYear().getId());
    }

    public int getSemester() {
        return semester;
    }
}
