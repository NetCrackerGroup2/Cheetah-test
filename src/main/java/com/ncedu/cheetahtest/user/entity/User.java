package com.ncedu.cheetahtest.user.entity;

import lombok.Data;

@Data
public class User {

    private int id;
    private String email;
    private String pass;
    private String name;
    private String role;
    private String status;
    private int resetPassToken;

    public User() {
    }

    public User(int id, String email, String pass, String name, String role, String status, int resetPassToken) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.role = role;
        this.status = status;
        this.resetPassToken = resetPassToken;
    }

    public User(int id) {
        this.id = id;
    }

}
