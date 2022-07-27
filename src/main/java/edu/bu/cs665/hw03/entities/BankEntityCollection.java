package edu.bu.cs665.hw03.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Collection of Entities in a bank. It makes no assumption of how the entities
 * are actually stored and how they are indexed.
 *
 * @param <T> type of subclass BankEntity
 * @see BankEntity
 * @author dlegaspi@bu.edu
 */
public class BankEntityCollection<T extends BankEntity> {
    private final List<T> list = new ArrayList<>();

    public final List<T> asList() {
        return list;
    }

    public Optional<T> findEntity(String id) {
        return list.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public boolean contains(T entity) {
        return list.contains(entity);
    }

    public void add(T entity) {
        if (!contains(entity)) {
            list.add(entity);
        }
    }

    public void remove(T entity) {
        if (contains(entity)) {
            list.remove(entity);
        }
    }
}
