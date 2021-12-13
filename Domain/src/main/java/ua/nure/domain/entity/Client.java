package ua.nure.domain.entity;

import java.util.Objects;

public class Client extends Entity {
    String phoneNumber;
    String password;
    String login;
    String name;

    public Client(Integer identifier, String login, String name, String password, String phoneNumber) {
        super(identifier);
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
        this.login = login;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
        return object instanceof Client && ((Client) object).getLogin().equals(login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, phoneNumber, password, name);
    }

    @Override
    public String toString() {
        return "Client{" +
                "identifier=" + getIdentifier() +
                ", login='" + login + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
