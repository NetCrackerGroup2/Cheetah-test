package com.ncedu.cheetahtest.security.entity;

import lombok.Data;

@Data
public class RegisterResponse {
    private String status;

    public RegisterResponse(String status) {
        this.status = status;
    }
}
