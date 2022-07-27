package edu.bu.cs665.hw03;

import edu.bu.cs665.hw03.controllers.banks.Bank;
import edu.bu.cs665.hw03.controllers.banks.BankOfAmerica;
import edu.bu.cs665.hw03.controllers.banks.Chase;
import edu.bu.cs665.hw03.entities.customers.CustomerType;
import edu.bu.cs665.hw03.entities.customers.factories.factorymethod.BusinessCustomerFactory;
import edu.bu.cs665.hw03.entities.customers.factories.factorymethod.CustomerFactory;
import edu.bu.cs665.hw03.entities.customers.factories.factorymethod.PreferredCustomerFactory;
import edu.bu.cs665.hw03.entities.customers.factories.factorymethod.StandardCustomerFactory;
import edu.bu.cs665.hw03.entities.customers.factories.simple.SimpleCustomerFactory;
import edu.bu.cs665.hw03.views.BankOfAmericaUIBuilder;
import edu.bu.cs665.hw03.views.BankUIDirector;
import edu.bu.cs665.hw03.views.ChaseUIBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Creational patterns tests
 *
 * @author dlegaspi@bu.edu
 */
public class CreationalPatternsTests {
    @Test
    @DisplayName("Part 01: Abstract Factory Test")
    public void part01() {
        var banks = List.of(Bank.getInstance(),
                        BankOfAmerica.getInstance(),
                        Chase.getInstance());

        banks.forEach(bank -> {
            System.out.format("--Testing %s version of Accounts--\n", bank.getName());

            System.out.println("Creating Checking Account");
            var checkingAccount = bank.createCheckingAccount();
            assertNotNull(checkingAccount);
            System.out.println(checkingAccount);

            System.out.println("Creating Savings Account");
            var savingsAccount = bank.createSavingsAccount();
            System.out.println(savingsAccount);
            System.out.println();
        });

    }

    @Test
    @DisplayName("Part 02: Builder Pattern Test")
    public void part02() {
        var director = BankUIDirector.getInstance();
        var builders = List.of(BankOfAmericaUIBuilder.getInstance(), ChaseUIBuilder.getInstance());

        builders.forEach(builder -> {
            System.out.format("--Testing %s version of UI--\n\n", builder.getBank().getName());
            director.construct(builder);
            var gui = builder.getUI();
            gui.showConstructionSteps(System.out::print);
            System.out.println();
        });


    }

    @Test
    @DisplayName("Part 03: Simple Factory and Factory Method Test")
    public void part03() {
        // simple factory
        System.out.println("Part3 First version using SimpleFactory");
        Arrays.stream(CustomerType.values()).sequential().forEach(type -> {
            System.out.format("Creating %s Customer...\n", type.toString());
            var c = SimpleCustomerFactory.getInstance().createCustomer(type);
            assertNotNull(c);
            System.out.println(c);
        });

        System.out.println();

        // factory method
        System.out.println("Part3 Second version using Factory Method");

        // we are using TreeMap to preserve order of insertion
        var factoryMap = new TreeMap<CustomerType, CustomerFactory>() {
            {
                put(CustomerType.Standard, StandardCustomerFactory.getInstance());
                put(CustomerType.Preferred, PreferredCustomerFactory.getInstance());
                put(CustomerType.Business, BusinessCustomerFactory.getInstance());
            }
        };

        factoryMap.forEach((type, factory) -> {
            System.out.format("Creating %s Customer...\n", type);
            var c = factory.createCustomer();
            assertNotNull(c);
            System.out.println(c);
        });
    }
}
