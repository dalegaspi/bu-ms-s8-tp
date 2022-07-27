package edu.bu.cs665.hw03.entities.customers.factories.factorymethod;

import edu.bu.cs665.hw03.entities.customers.Customer;
import edu.bu.cs665.hw03.entities.customers.PreferredCustomer;

public class PreferredCustomerFactory extends CustomerFactory {
    private final static CustomerFactory instance = new PreferredCustomerFactory();

    public static CustomerFactory getInstance() {
        return instance;
    }

    @Override
    public Customer createCustomer() {
        return new PreferredCustomer();
    }
}
