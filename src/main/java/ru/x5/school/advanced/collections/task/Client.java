package ru.x5.school.advanced.collections.task;
import java.util.Objects;

public class Client {
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;

    public Client(String firstName, String lastName, String phoneNumber) {
        if (firstName == null || lastName == null || phoneNumber == null) {
            throw new IllegalArgumentException("All client fields should be non null");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return firstName.equals(client.firstName) && lastName.equals(client.lastName) && phoneNumber.equals(client.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber);
    }
}
