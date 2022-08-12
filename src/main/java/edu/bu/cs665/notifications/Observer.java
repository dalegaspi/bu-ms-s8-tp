package edu.bu.cs665.notifications;

import java.util.function.Consumer;

/**
 * Observer
 *
 * No need to define a new class...extend the existing Consumer object
 *
 * @see Event
 * @see Consumer
 * @param <T> type
 * @author dlegaspi@bu.edu
 */
public interface Observer<T extends Event> extends Consumer<T> {
}
