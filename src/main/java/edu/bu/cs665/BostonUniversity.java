package edu.bu.cs665;

import edu.bu.cs665.course.SchoolYear;

import java.util.List;
import java.util.Optional;

/**
 * The university
 *
 * @author dlegaspi@bu.edu
 */
public final class BostonUniversity {
    private static final BostonUniversity instance = new BostonUniversity();

    private List<Department> departments;

    private BostonUniversity() {
        var builders = List.of(ComputerScienceDepartment.getInstance().getBuilder());

        // BU acts as the director of department builders
        this.departments = builders.stream().map(b -> {
            b.addConcentrations();
            b.addPrograms();
            b.addFaculty();
            b.addCourses(SchoolYear.fromCurrentYear());
            return b.build();
        }).toList();
    }

    public static BostonUniversity getInstance() {
        return instance;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public Optional<Department> findDepartment(String name) {
        return getDepartments().stream().filter(d -> d.getName().equalsIgnoreCase(name)).findFirst();
    }
}
