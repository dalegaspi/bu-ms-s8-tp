package edu.bu.cs665;

import java.util.List;
import java.util.function.Function;

public interface GpaComputeStrategy extends Function<List<EnrolledCourse>, Double> {
}
