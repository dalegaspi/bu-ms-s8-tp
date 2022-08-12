package edu.bu.cs665.formatting;

/**
 * Formatting to HTML
 *
 * @author dlegaspi@bu.edu
 */
public interface HyperTextMarkupFormatter {
    String format(boolean fragment);

    public static final String P = "p";
    public static final String ROOT = "html";

    static String wrapTag(String tag, String element) {
        return startTag(tag) + element + endTag(tag);
    }

    static String startTag(String tag) {
        return String.format("<%s>\n", tag);
    }

    static String endTag(String tag) {
        return String.format("</%s>\n", tag);
    }
}
