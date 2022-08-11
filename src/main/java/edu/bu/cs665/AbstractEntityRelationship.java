package edu.bu.cs665;

import java.util.Objects;
import java.util.UUID;

public class AbstractEntityRelationship implements EntityRelationship {
    protected String id = generateId();

    @Override
    public String getId() {
        return id;
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AbstractEntityRelationship that = (AbstractEntityRelationship) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
