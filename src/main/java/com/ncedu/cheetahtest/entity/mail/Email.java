package com.ncedu.cheetahtest.entity.mail;

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
