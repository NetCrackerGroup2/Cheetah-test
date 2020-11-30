package com.ncedu.cheetahtest.config.security.jwt;

import com.ncedu.cheetahtest.entity.user.User;


public interface JwtTokenProvider {
    String createToken(User user);
    String getEmail(String token);
    boolean isTokenValid(String token);
}
