package edu.bu.cs665.program;

import edu.bu.cs665.AbstractEntity;
import org.checkerframework.checker.units.qual.C;

public class Program extends AbstractEntity {
    private final String title;

    public Program(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static Program createCertificateProgram(String title) {
        return new CertificateProgram(title);
    }

    public static Program createGraduateProgram(String title, int totalCoreCourses, int totalElectiveCourses) {
        var p = new GraduateProgram(title);
        p.setTotalCoreCourses(totalCoreCourses);
        p.setTotalCoreCourses(totalElectiveCourses);

        return p;
    }

    public static Program createUndergraduateProgram(String title, int totalCoreCourses, int totalElectiveCourses) {
        var p = new UndergraduateProgram(title);
        p.setTotalCoreCourses(totalCoreCourses);
        p.setTotalCoreCourses(totalElectiveCourses);

        return p;
    }
}
