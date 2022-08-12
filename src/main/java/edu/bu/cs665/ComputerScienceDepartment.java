package edu.bu.cs665;

import edu.bu.cs665.course.SchoolYear;

import java.awt.event.WindowStateListener;

/**
 * The Computer Science Department
 *
 * @see Department
 * @author dlegaspi@bu.edu
 */
public final class ComputerScienceDepartment extends Department {
    private static final ComputerScienceDepartment instance = new ComputerScienceDepartment();

    public static Department getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "Computer Science";
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Department> DepartmentBuilder<T> getBuilder() {
        return (DepartmentBuilder<T>) new ComputerScienceDepartmentBuilder();
    }

    public static class ComputerScienceDepartmentBuilder implements DepartmentBuilder<ComputerScienceDepartment> {

        private final ComputerScienceDepartment instance = new ComputerScienceDepartment();

        @Override
        public void addConcentrations() {

        }

        @Override
        public void addFaculty() {

        }

        @Override
        public void addPrograms() {

        }

        @Override
        public void addCourses(SchoolYear year) {

        }

        public ComputerScienceDepartment build() {
            return instance;
        }
    }
}
