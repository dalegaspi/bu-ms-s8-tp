package edu.bu.cs665.hw03.views;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * Base UI
 *
 * @author dlegaspi@bu.edu
 */
public class BankUI {
    List<String> parts = new ArrayList<>();

    public void add(String part) {
        parts.add(part);
    }

    public void showConstructionSteps(Consumer<String> callback) {
        callback.accept("User interface completed as below :\n");
        IntStream.range(0, parts.size()).forEach(i -> {
            callback.accept(String.format("Step %d: Creating %s...\n", i + 1, parts.get(i)));
        });
    }
}
