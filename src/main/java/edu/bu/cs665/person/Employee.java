package edu.bu.cs665.person;

import edu.bu.cs665.course.Concentration;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Employee extends Person {

    private final Set<Title> titles = new HashSet<>();

    public Employee(String name) {
        super(name);
    }

    public Set<Title> getTitles() {
        return titles;
    }

    @SuppressWarnings("unchecked")
    public <T extends Title> Optional<T> getTitleOfType(Class<T> type) {
        return (Optional<T>) getTitles().stream().filter(type::isInstance).findFirst();
    }

    public boolean hasTitleType(Class<? extends Title> type) {
        return getTitleOfType(type).isPresent();
    }

    public boolean isAdvisor() {
        return hasTitleType(Advisor.class);
    }

    public boolean isChairPerson() {
        return hasTitleType(ChairPerson.class);
    }

}
