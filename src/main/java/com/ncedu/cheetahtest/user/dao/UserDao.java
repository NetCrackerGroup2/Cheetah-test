package com.ncedu.cheetahtest.user.dao;

import com.ncedu.cheetahtest.user.entity.User;
import com.ncedu.cheetahtest.user.entity.ResetToken;

public interface UserDao {

    void createDeveloper(User user);

    User findUserByEmail(String email);

    User findUserByEmailAndPassword(String email, String password);

    void saveToken(ResetToken myToken);

    void changeUserPassword(ResetToken resetToken, String password);

    ResetToken findResetTokenByToken(String token);

    User findUserByToken(String token);

    ResetToken findResetTokenByUserId(int id);

    void createToken(ResetToken myToken);

    void makeTokenExpired(ResetToken resetToken);
}
