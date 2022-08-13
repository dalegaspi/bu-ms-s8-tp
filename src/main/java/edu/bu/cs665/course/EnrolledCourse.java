package edu.bu.cs665.course;

import edu.bu.cs665.entity.AbstractEntityRelationship;
import edu.bu.cs665.grade.CourseGrade;

import java.util.Collection;

public class EnrolledCourse extends AbstractEntityRelationship {
    private final Course course;
    private final Semester semester;
    private CourseGrade grade;

    private final ClassOffering classOffering;

    public EnrolledCourse(Course course, Semester semester, ClassOffering classOffering) {
        this.course = course;
        this.semester = semester;
        this.classOffering = classOffering;
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

    public static int totalCoursesUnits(Collection<EnrolledCourse> enrolledCourses) {
        return enrolledCourses.stream().map(c -> c.getCourse().getUnits()).mapToInt(Integer::intValue).sum();
    }

    public static int totalCoursesGrades(Collection<EnrolledCourse> enrolledCourses) {
        return enrolledCourses.stream().map(c -> c.getGrade().getGradeValue()).mapToInt(Integer::intValue).sum();
    }

    public ClassOffering getClassOffering() {
        return classOffering;
    }
}
