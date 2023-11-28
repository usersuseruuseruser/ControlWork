package com.solncev.net.model;

public class User {

    private int id;

    private String name;

    private String lastname;

    private String email;

    private String login;

    private String password;

    public User(String name, String lastname, String email, String login, String password) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public User(int id, String name, String lastname, String email, String login, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.login = login;
        this.password = password;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
