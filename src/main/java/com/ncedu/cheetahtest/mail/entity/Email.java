package com.ncedu.cheetahtest.mail.entity;

import lombok.Data;

@Data
public class Email {
    private String email;

    public Email() {
    }

    public Email(String email) {
        this.email = email;
    }
}
