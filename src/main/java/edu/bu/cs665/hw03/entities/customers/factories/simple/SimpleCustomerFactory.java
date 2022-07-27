package edu.bu.cs665.hw03.entities.customers.factories.simple;

import edu.bu.cs665.hw03.entities.customers.*;

/**
 * Simple Customer factory
 *
 * @author dlegaspi@bu.edu
 */
public class SimpleCustomerFactory {
    private static final SimpleCustomerFactory instance = new SimpleCustomerFactory();

    public static SimpleCustomerFactory getInstance() {
        return instance;
    }

    public Customer createCustomer(CustomerType type) {
        return switch (type) {
        case Standard -> new StandardCustomer();
        case Preferred -> new PreferredCustomer();
        case Business -> new BusinessCustomer();
        };
    }
}
