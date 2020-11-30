package com.ncedu.cheetahtest.security.jwt;

import com.ncedu.cheetahtest.user.entity.User;


public interface JwtTokenProvider {
    String createToken(User user);
    String getEmail(String token);
    boolean isTokenValid(String token);
}
