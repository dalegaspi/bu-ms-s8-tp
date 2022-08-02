package edu.bu.cs665.person.employee;

import edu.bu.cs665.person.Person;
import edu.bu.cs665.person.Title;

import java.util.Optional;
import java.util.Set;

public class Employee extends Person {

    private Set<Title> titles;

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

    public boolean isFaculty() {
        return hasTitleType(Faculty.class);
    }

    public boolean isAdvisor() {
        return hasTitleType(Advisor.class);
    }

    public boolean isPartTimeFaculty() {
        Optional<Faculty> t = getTitleOfType(Faculty.class);
        return t.map(Faculty::isFullTime).orElse(false);
    }
}
