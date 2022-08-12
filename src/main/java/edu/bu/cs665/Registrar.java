package edu.bu.cs665;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller that deals with the validation of enrollments
 *
 * @author dlegaspi@bu.edu
 */
public final class Registrar {

    public static final Registrar instance = new Registrar();

    public Registrar() {
        this.classOfferings = new ArrayList<>();
    }

    public static Registrar getInstance() {
        return instance;
    }

    private final List<ClassOffering> classOfferings;

    public void addOffering(@NonNull final ClassOffering classOffering) {
        classOfferings.add(classOffering);
    }

    public List<ClassOffering> getClassOfferings() {
        return classOfferings;
    }
}
