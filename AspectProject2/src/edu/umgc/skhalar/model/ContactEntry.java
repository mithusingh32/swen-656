package edu.umgc.skhalar.model;

import java.util.Objects;

public class ContactEntry {
    private final String firstName;
    private final String lastName;
    private final String streetAddress;
    private final String city;
    private final String state;
    private final String country;
    private final String phoneNumber;

    public ContactEntry(String firstName, String lastName, String streetAddress, String city, String state,
                        String country, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets firstName
     *
     * @return value of {@link ContactEntry#firstName}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets lastName
     *
     * @return value of {@link ContactEntry#lastName}
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets streetAddress
     *
     * @return value of {@link ContactEntry#streetAddress}
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Gets city
     *
     * @return value of {@link ContactEntry#city}
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets state
     *
     * @return value of {@link ContactEntry#state}
     */
    public String getState() {
        return state;
    }

    /**
     * Gets country
     *
     * @return value of {@link ContactEntry#country}
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets phoneNumber
     *
     * @return value of {@link ContactEntry#phoneNumber}
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContactEntry that = (ContactEntry) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName)
                && Objects.equals(streetAddress, that.streetAddress) && Objects.equals(city, that.city)
                && Objects.equals(state, that.state) && Objects.equals(country, that.country)
                && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, streetAddress, city, state, country, phoneNumber);
    }
}
