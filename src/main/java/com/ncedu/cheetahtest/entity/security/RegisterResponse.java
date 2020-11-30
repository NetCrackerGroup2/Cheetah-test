package com.ncedu.cheetahtest.entity.security;

import lombok.Data;

@Data
public class RegisterResponse {
    private String status;

    public RegisterResponse(String status) {
        this.status = status;
    }
}
