package com.ncedu.cheetahtest.security.entity;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
