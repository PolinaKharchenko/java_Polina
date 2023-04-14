package ru.stqa.pft.mantis.model;

public class User {
    private int id;
    private String username;
    private String email;
    private String userpassword;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public int getId() {
        return id;
    }

    public User(int id, String username, String email, String userpassword){
        this.id = id;
        this.username = username;
        this.email = email;
        this.userpassword = userpassword;

    }
}
