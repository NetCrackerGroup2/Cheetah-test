package com.ncedu.cheetahtest.security.entity;

import lombok.Data;

@Data
public class AccessTokenDto {
    String accessToken;

    public AccessTokenDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
