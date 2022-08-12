package edu.bu.cs665.course;

import edu.bu.cs665.ds.TreeNode;
import edu.bu.cs665.formatting.HyperTextMarkupFormatter;

import java.util.stream.Collectors;

import static edu.bu.cs665.formatting.HyperTextMarkupFormatter.wrapTag;

/**
 * Concentration hierarchy
 *
 * @see Concentration
 * @see TreeNode
 * @author dlegaspi@bu.edu
 */
public class ConcentrationGroup extends TreeNode<Concentration> implements HyperTextMarkupFormatter {
    public ConcentrationGroup(Concentration data) {
        super(data);
    }

    @Override
    public String format(boolean fragment) {
        var html = wrapTag(P, data.getName()) +
                        children.stream().map(c -> format(false)).collect(Collectors.joining());
        return fragment ? html : wrapTag(ROOT, html);
    }
}
