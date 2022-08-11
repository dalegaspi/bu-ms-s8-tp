package edu.bu.cs665.course;

import edu.bu.cs665.course.Course;
import edu.bu.cs665.person.Faculty;

import java.util.List;

public class Concentration {
    private List<Course> courses;

    private Faculty coordinator;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void format() {

    }
}
