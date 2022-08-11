package edu.bu.cs665.program;

import edu.bu.cs665.AbstractEntity;

public class Program extends AbstractEntity {
    private final String title;

    public Program(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
