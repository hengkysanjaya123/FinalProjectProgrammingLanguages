package model;

import java.util.Date;

public class Person {
    private int id;
    private String name;
    private Date birthdate;
    private String gender;

    public Person() {
    }

    public Person(int id, String name, Date birthdate, String gender) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
