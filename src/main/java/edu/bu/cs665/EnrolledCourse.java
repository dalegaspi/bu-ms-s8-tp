package edu.bu.cs665;

import edu.bu.cs665.course.Course;
import edu.bu.cs665.course.Semester;

public class EnrolledCourse {
    private final Course course;
    private final Semester semester;
    private CourseGrade grade;

    public EnrolledCourse(Course course, Semester semester) {
        this.course = course;
        this.semester = semester;
    }

    public Semester getSemester() {
        return semester;
    }

    public Course getCourse() {
        return course;
    }

    public CourseGrade getGrade() {
        return grade;
    }

    public void setGrade(CourseGrade grade) {
        this.grade = grade;
    }
}
