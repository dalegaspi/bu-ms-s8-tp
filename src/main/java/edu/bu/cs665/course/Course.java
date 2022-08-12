package edu.bu.cs665.course;

import edu.bu.cs665.AbstractEntity;
import edu.bu.cs665.Entity;
import edu.bu.cs665.formatting.HyperTextMarkupFormatter;

import javax.swing.text.html.HTML;

import static edu.bu.cs665.formatting.HyperTextMarkupFormatter.wrapTag;

public class Course extends AbstractEntity implements HyperTextMarkupFormatter {
    public static final int UNITS = 4;

    private String code;

    public Course(String code, String title, String description) {
        this.description = description;
        this.title = title;
        this.code = code;
        this.syllabus = Syllabus.createBasicSyllabus(this);
    }

    public int getUnits() {
        return UNITS;
    }

    private final String description;

    private final String title;

    private Syllabus syllabus;

    @Override
    public String getId() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String format(boolean fragment) {
        var html = wrapTag(P, syllabus.getDescription());
        return fragment ? html : wrapTag(ROOT, html);
    }
}
