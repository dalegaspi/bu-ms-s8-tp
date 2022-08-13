package edu.bu.cs665.course;

import edu.bu.cs665.entity.AbstractEntityRelationship;
import edu.bu.cs665.grade.CourseGrade;

import java.util.Collection;

/**
 * Enrolled course record for a Student
 *
 * @see edu.bu.cs665.person.Student
 * @author dlegaspi@bu.edu
 */
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
        return enrolledCourses.stream().filter(c -> c.getGrade() != null).map(c -> c.getCourse().getUnits())
                        .mapToInt(Integer::intValue).sum();
    }

    public static int totalCoursesGrades(Collection<EnrolledCourse> enrolledCourses) {
        return enrolledCourses.stream().filter(c -> c.getGrade() != null)
                        .map(c -> c.getGrade().getGradeValue() * c.getCourse().getUnits())
                        .mapToInt(Integer::intValue).sum();
    }

    public ClassOffering getClassOffering() {
        return classOffering;
    }

    public static EnrolledCourse createEnrolledCourse(Course course, Semester semester, ClassOffering classOffering) {
        return new EnrolledCourse(course, semester, classOffering);
    }
}
