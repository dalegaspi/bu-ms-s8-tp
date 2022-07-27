package edu.bu.cs665.hw03.entities.customers.factories.factorymethod;

import edu.bu.cs665.hw03.entities.customers.BusinessCustomer;
import edu.bu.cs665.hw03.entities.customers.Customer;

public class BusinessCustomerFactory extends CustomerFactory {
    private final static CustomerFactory instance = new BusinessCustomerFactory();

    public static CustomerFactory getInstance() {
        return instance;
    }

    @Override
    public Customer createCustomer() {
        return new BusinessCustomer();
    }
}
