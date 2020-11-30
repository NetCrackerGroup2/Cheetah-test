package com.ncedu.cheetahtest.user.service;

import com.ncedu.cheetahtest.user.entity.User;
import com.ncedu.cheetahtest.user.entity.ResetToken;

public interface UserService {

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    ResetToken findByToken(String token);

    void makeTokenExpired(ResetToken resetToken);
}
