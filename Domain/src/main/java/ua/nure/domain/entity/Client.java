package ua.nure.domain.entity;

import java.util.Objects;

public class Client extends Entity {
    String phoneNumber;
    String password;
    String name;

    public Client(Object identifier, String name, String password, String phoneNumber) {
        super(identifier);
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Client && ((Client) object).getIdentifier().equals(getIdentifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phoneNumber, password, name);
    }

    @Override
    public String toString() {
        return "Client{" +
                "identifier='" + getIdentifier() + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
