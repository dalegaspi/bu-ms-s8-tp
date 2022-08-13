package edu.bu.cs665.course;

import edu.bu.cs665.course.Course;
import edu.bu.cs665.course.Semester;
import edu.bu.cs665.entity.AbstractEntityRelationship;
import edu.bu.cs665.person.Faculty;
import edu.bu.cs665.person.Student;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.*;

public class ClassOffering extends AbstractEntityRelationship {
    private final Course course;
    private final Faculty professor;
    private final Semester semester;
    private final int enrollmentLimit;

    private final Set<Student> students = new HashSet<>();

    public ClassOffering(Course course, Faculty professor, Semester semester, int enrollmentLimit) {
        this.course = course;
        this.professor = professor;
        this.semester = semester;
        this.enrollmentLimit = enrollmentLimit;
    }

    public Course getCourse() {
        return course;
    }

    public Faculty getProfessor() {
        return professor;
    }

    public Semester getSemester() {
        return semester;
    }

    public int getEnrollmentLimit() {
        return enrollmentLimit;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void addStudent(@NonNull final Student student) {
        students.add(student);
    }

    public void removeStudent(@NonNull final Student student) {
        students.remove(student);
    }

    public boolean isFull() {
        return getStudents().size() >= getEnrollmentLimit();
    }

    @Override
    public String toString() {
        return String.format("Class offering for [%s] Semester [%s] Professor [%s] Limit [%d]",
                        getCourse().getTitle(), getSemester().getSemester(), getProfessor().getName(),
                        getEnrollmentLimit());
    }
}
