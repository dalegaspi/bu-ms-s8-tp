package edu.bu.cs665.course;

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
public class CourseGroup implements HyperTextMarkupFormatter {
    private final List<Course> courses;

    private Faculty coordinator;

    private final String name;

    public String getName() {
        return name;
    }

    public void addCourse(@NonNull Course course) {
        courses.add(course);
    }

    public CourseGroup(@NonNull String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    @Override
    public String format(boolean topLevel) {
        var html = courses.stream().map(c -> c.format(false)).collect(Collectors.joining());
        return !topLevel ? html : wrapTag(ROOT, html);
    }

    public Faculty getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Faculty coordinator) {
        this.coordinator = coordinator;
    }
}
