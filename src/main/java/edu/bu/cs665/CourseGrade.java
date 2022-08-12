package edu.bu.cs665;

public enum CourseGrade {
    A(4),
    B(3),
    C(2),
    D(1),
    F(0);

    private final int gradeValue;

    CourseGrade(int gradeValue) {
        this.gradeValue = gradeValue;
    }

    public int getGradeValue() {
        return gradeValue;
    }
}
