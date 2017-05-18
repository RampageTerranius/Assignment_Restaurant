package com.example.tyler.assignmentrestaurant;

/**
 * Created by Tyler on 4/05/2017.
 */

public class Login {
    private String user;
    private String password;

    public Login(String pUser, String pPassword) {
        setUser(pUser);
        setPassword(pPassword);
    }

    public void setUser(String pUser) {
        user = pUser;
    }
    public String getUser() {
        return user;
    }

    public void setPassword(String pPassword) {
        password = pPassword;
    }
    public String getPassword() { return password; }
}
