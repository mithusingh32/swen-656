package edu.umgc.skhalar.model;

import java.util.Objects;
import java.util.StringJoiner;

public class ContactEntry {
    private final String firstName;
    private final String lastName;
    private final String streetAddress;
    private final String city;
    private final String state;
    private final String zipcode;
    private final String phoneNumber;

    public ContactEntry(String firstName, String lastName, String streetAddress, String city, String state,
                        String zipcode, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
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
     * Gets zip code
     *
     * @return value of {@link ContactEntry#zipcode}
     */
    public String getZipcode() {
        return zipcode;
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
                && Objects.equals(state, that.state) && Objects.equals(zipcode, that.zipcode)
                && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, streetAddress, city, state, zipcode, phoneNumber);
    }

	@Override
	public String toString() {
		return new StringJoiner(" ")
				.add(firstName)
				.add(lastName)
				.add(streetAddress)
				.add(city)
				.add(state)
				.add(zipcode)
				.add(phoneNumber)
				.toString();
	}
    
}
