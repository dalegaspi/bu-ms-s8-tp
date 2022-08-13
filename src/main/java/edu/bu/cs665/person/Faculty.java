package edu.bu.cs665.person;

import edu.bu.cs665.course.*;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Faculty extends Employee {
    private final static Logger logger = Logger.getLogger(Faculty.class.getName());

    private final Set<Concentration> coordinatedConcentrations = new HashSet<>();

    public Collection<Concentration> getCoordinatedConcentrations() {
        return coordinatedConcentrations;
    }

    public void addCoordinatedConcentration(Concentration concentration) {
        coordinatedConcentrations.add(concentration);
    }

    private boolean fullTime;

    public Faculty(String name) {
        super(name);
    }

    public boolean isFullTime() {
        return fullTime && !isChairPerson();
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    private final Set<ClassOffering> classesTaught = new HashSet<>();

    private final Map<Semester, Set<Student>> advisedStudents = new HashMap<>();

    public void addAdvisedStudent(@NonNull Semester semester, @NonNull Student student) {
        if (!advisedStudents.containsKey(semester)) {
            advisedStudents.put(semester, new HashSet<>());
        }

        advisedStudents.get(semester).add(student);
    }

    public Collection<Student> getAdvisedStudents(@NonNull Semester semester) {
        return advisedStudents.getOrDefault(semester, Set.of());
    }

    public void addClassOffering(@NonNull ClassOffering classOffering) {
        classesTaught.add(classOffering);
    }

    public Collection<Course> getClassesTaught(@NonNull Semester semester) {
        return classesTaught.stream().filter(co -> co.getSemester().equals(semester)).map(ClassOffering::getCourse)
                        .toList();
    }

    public String getFullStatus(@NonNull Semester semester) {
        String msg = String.format("Faculty: %s (%s)\n", getName(), isFullTime() ? "Full-Time" : "Part-Time");
        msg += String.format("Classes for Semester %s:\n", semester);
        msg += getClassesTaught(semester).stream().map(c -> String.format("- %s\n", c)).collect(Collectors.joining());
        msg += String.format("Advised Students for Semester %s:\n", semester);
        if (getAdvisedStudents(semester).isEmpty()) {
            msg += "- None";
        } else {
            msg += getAdvisedStudents(semester).stream().map(s -> String.format("- %s\n", s.getName()))
                            .collect(Collectors.joining());
        }

        return msg;
    }

    public void emitFullStatus(@NonNull Semester semester) {
        logger.info("\n-----\n" + getFullStatus(semester) + "\n-----");
    }
}
