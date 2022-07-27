package edu.bu.cs665.hw03.entities.customers.factories.factorymethod;

import edu.bu.cs665.hw03.entities.customers.Customer;
import edu.bu.cs665.hw03.entities.customers.StandardCustomer;

public class StandardCustomerFactory extends CustomerFactory {
    private final static CustomerFactory instance = new StandardCustomerFactory();

    public static CustomerFactory getInstance() {
        return instance;
    }

    @Override
    public Customer createCustomer() {
        return new StandardCustomer();
    }
}
