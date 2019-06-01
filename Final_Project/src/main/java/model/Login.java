package model;

public class Login implements IModel {
    public String email;
    public String password;

    public Login() {
    }

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
