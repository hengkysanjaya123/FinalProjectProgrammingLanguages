package model;

public class LoginResult implements IModel{
    public String email;
    public String password;
    public String role;

    public LoginResult() {
    }

    public LoginResult(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}