package edu.bu.cs665.grade;

import edu.bu.cs665.course.EnrolledCourse;

import java.util.List;
import java.util.function.Function;

/**
 * Computation of GPA
 *
 * @author dlegaspi@bu.edu
 */
public interface GpaComputeStrategy extends Function<List<EnrolledCourse>, Double> {
}
