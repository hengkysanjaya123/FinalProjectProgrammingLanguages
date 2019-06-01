package model;

import java.util.Date;

public class Seller extends Person implements IModel{
    private int sellerId;
    private String email;
    private String password;

    public Seller() {
    }

    public Seller(int id, String name, Date birthdate, String gender, int sellerId, String email, String password) {
        super(id, name, birthdate, gender);
        this.sellerId = sellerId;
        this.email = email;
        this.password = password;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
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
}
