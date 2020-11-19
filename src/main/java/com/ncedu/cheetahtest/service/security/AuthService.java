package com.ncedu.cheetahtest.service.security;

import com.ncedu.cheetahtest.entity.user.ResetToken;
import com.ncedu.cheetahtest.entity.mail.PasswordDTO;
import com.ncedu.cheetahtest.entity.security.AccessTokenDto;
import com.ncedu.cheetahtest.entity.security.LoginDto;
import com.ncedu.cheetahtest.entity.security.RegisterDto;

public interface AuthService {
    void register(RegisterDto registerDto);
    AccessTokenDto login(LoginDto loginDto);

    void changeUserPassword(ResetToken resetToken, String password);
    boolean validatePassword(PasswordDTO passwordDTO);
}
