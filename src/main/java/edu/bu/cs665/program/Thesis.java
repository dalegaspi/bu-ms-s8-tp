package edu.bu.cs665.program;

import edu.bu.cs665.person.Faculty;

import java.util.Optional;

public class Thesis {
    private final String title;
    private Faculty advisor;

    public Thesis(String title) {
        this.title = title;
    }

    public Optional<Faculty> getAdvisor() {
        return Optional.ofNullable(advisor);
    }

    public void setAdvisor(Faculty advisor) {
        this.advisor = advisor;
    }
}
