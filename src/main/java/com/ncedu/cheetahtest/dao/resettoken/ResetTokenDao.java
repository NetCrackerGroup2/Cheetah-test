package com.ncedu.cheetahtest.dao.resettoken;

import com.ncedu.cheetahtest.entity.user.ResetToken;

public interface ResetTokenDao {
    void saveToken(ResetToken myToken);

    ResetToken findResetTokenByToken(String token);

    void createToken(ResetToken myToken);

    void makeTokenExpired(ResetToken resetToken);

    ResetToken findResetTokenByUserId(int id);

}
