package edu.bu.cs665.ds;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple Tree implementation
 *
 * @param <T> type
 * @author dlegaspi@bu.edu
 */
public class TreeNode<T> {
    protected List<TreeNode<T>> children = new ArrayList<>();
    protected T data;

    public TreeNode(T data) {
        this.data = data;
    }

    public void add(TreeNode<T> node) {
        children.add(node);
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }
}
