package edu.bu.cs665;

import java.util.List;
import java.util.function.Function;

/**
 * Computation of GPA
 *
 * @author dlegaspi@bu.edu
 */
public interface GpaComputeStrategy extends Function<List<EnrolledCourse>, Double> {
}
