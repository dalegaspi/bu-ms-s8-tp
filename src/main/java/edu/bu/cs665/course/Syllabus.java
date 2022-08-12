package edu.bu.cs665.course;

import edu.bu.cs665.AbstractEntity;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Syllabus
 *
 * @author dlegaspi@bu.edu
 */
public class Syllabus extends AbstractEntity {
    private final String description;

    public Syllabus(String description) {
        this.description = description;
    }

    public static Syllabus createBasicSyllabus(@NonNull Course course) {
        return new Syllabus(String.format("Syllabus for %s", course.getId()));
    }

    public String getDescription() {
        return description;
    }
}
