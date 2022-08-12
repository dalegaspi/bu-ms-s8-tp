package edu.bu.cs665.program;

import edu.bu.cs665.course.Semester;
import edu.bu.cs665.person.Faculty;

import java.util.Optional;

public class Thesis {
    private final String title;
    private Faculty advisor;

    private Semester semester;

    public Thesis(String title) {
        this.title = title;
    }

    public Optional<Faculty> getAdvisor() {
        return Optional.ofNullable(advisor);
    }

    public void setAdvisor(Faculty advisor) {
        this.advisor = advisor;
    }

    public String getTitle() {
        return title;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }
}
