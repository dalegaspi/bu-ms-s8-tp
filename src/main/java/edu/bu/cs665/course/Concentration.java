package edu.bu.cs665.course;

import edu.bu.cs665.course.Course;
import edu.bu.cs665.formatting.HyperTextMarkupFormatter;
import edu.bu.cs665.person.Faculty;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static edu.bu.cs665.formatting.HyperTextMarkupFormatter.wrapTag;

/**
 * Concentration
 *
 * @author dlegaspi@bu.edu
 */
public class Concentration implements HyperTextMarkupFormatter {
    private final List<Course> courses = new ArrayList<>();

    private Faculty coordinator;

    private final String name;

    public String getName() {
        return name;
    }

    public void addCourse(@NonNull Course course) {
        courses.add(course);
    }

    public Concentration(@NonNull String name) {
        this.name = name;
    }

    @Override
    public String format(boolean fragment) {
        var html = wrapTag(P, getName()) +
                        courses.stream().map(c -> c.format(false)).collect(Collectors.joining());
        return fragment ? html : wrapTag(ROOT, html);
    }
}
