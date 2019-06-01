package model;

import java.util.Date;

public class Customer extends Person implements IModel {
    private int customerId;
    private String email;
    private String password;
    private long balance;

    public Customer() {
    }

    public Customer(int id, String name, Date birthdate, String gender, int customerId, String email, String password, long balance) {
        super(id, name, birthdate, gender);
        this.customerId = customerId;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
