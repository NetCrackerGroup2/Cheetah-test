package com.ncedu.cheetahtest.entity.security;

import lombok.Data;

@Data
public class AccessTokenDto {
    String accessToken;

    public AccessTokenDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
