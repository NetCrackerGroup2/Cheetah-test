package com.ncedu.cheetahtest.service.user;

import com.ncedu.cheetahtest.entity.user.ResetToken;
import com.ncedu.cheetahtest.entity.user.User;
import com.ncedu.cheetahtest.entity.user.UserDto;

import java.util.Date;

public interface UserService {

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    ResetToken findByToken(String token);

    void makeTokenExpired(ResetToken resetToken);

    void setUserLastRequest(String email, Date date);

    User editUser(UserDto user);

    User changeUserStatus(long id, String status);

    User findUserById(long id);
}
