package edu.bu.cs665.notifications;

public interface Subject<T extends Event> {
    void registerObserver(Observer<T> observer);

    void removeObserver(Observer<T> observer);

    void notifyObservers(T event);
}
