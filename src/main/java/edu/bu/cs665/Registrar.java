package edu.bu.cs665;

public class Registrar {
    private static final Registrar instance = new Registrar();

    public static Registrar getInstance() {
        return instance;
    }
}
