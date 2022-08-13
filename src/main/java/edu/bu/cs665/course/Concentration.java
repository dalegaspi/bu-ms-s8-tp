package edu.bu.cs665.course;

import edu.bu.cs665.ds.TreeNode;
import edu.bu.cs665.formatting.HyperTextMarkupFormatter;

import java.util.stream.Collectors;

import static edu.bu.cs665.formatting.HyperTextMarkupFormatter.wrapTag;

/**
 * Concentration hierarchy
 *
 * @see CourseGroup
 * @see TreeNode
 * @author dlegaspi@bu.edu
 */
public class Concentration extends TreeNode<CourseGroup> implements HyperTextMarkupFormatter {
    public Concentration(CourseGroup data) {
        super(data);
    }

    @Override
    public String format(boolean topLevel) {
        String html = "";
        if (!isLeaf()) {
            html += wrapTag(P, data.getName()) +
                            children.stream().map(c -> ((Concentration) c).format(false)).collect(Collectors.joining());
        } else {
            html += wrapTag(P, data.getName()) + data.format(false);
        }

        return !topLevel ? html : wrapTag(ROOT, html);
    }
}
