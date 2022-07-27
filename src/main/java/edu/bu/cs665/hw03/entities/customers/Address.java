package edu.bu.cs665.hw03.entities.customers;

/**
 * Address
 *
 * @author dlegaspi@bu.edu
 * @see AddressState
 */
public class Address {
    private String street;
    private String city;
    private AddressState state;
    private String zipCode;

    public Address(String street, String city, AddressState state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public AddressState getState() {
        return state;
    }

    public void setState(AddressState state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
