package edu.bu.cs665;

import edu.bu.cs665.course.SchoolYear;
import edu.bu.cs665.exceptions.SchoolException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * The university
 *
 * @author dlegaspi@bu.edu
 */
public final class BostonUniversity {
    private static final Logger logger = Logger.getLogger(BostonUniversity.class.getName());

    private static final BostonUniversity instance = new BostonUniversity();

    private final List<Department> departments;

    private BostonUniversity() {
        var builders = List.of(ComputerScienceDepartment.getInstance().getBuilder());

        // BU acts as the director of department builders
        this.departments = builders.stream().map(b -> {
            try {
                b.addPrograms();
                b.addFaculty();
                b.addCoursesAndConcentrations();
                b.addClassOfferings();
                return b.build();
            } catch (SchoolException exception) {
                logger.warning("Unable to create department: " + exception.getMessage());
                return null;
            }
        }).filter(Objects::nonNull).toList();
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
