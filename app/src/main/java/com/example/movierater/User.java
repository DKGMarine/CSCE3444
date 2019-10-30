package com.example.movierater;

public class User {
    private String email;
    private String password;
    private int user_id;
    public int[] favorites_list;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public int getUser_id() { return user_id;}

    public void setUser_id(int user_id) {this.user_id = user_id;}

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
