package ua.nure.domain.entity;

import java.util.Objects;

public class Client extends Entity {
    String phoneNumber;
    String password;
    String email;
    String name;

    public Client(Integer identifier, String email, String name, String password, String phoneNumber) {
        super(identifier);
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return object instanceof Client && ((Client) object).getEmail().equals(email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, phoneNumber, password, name);
    }

    @Override
    public String toString() {
        return "Client{" +
                "identifier=" + getIdentifier() +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
