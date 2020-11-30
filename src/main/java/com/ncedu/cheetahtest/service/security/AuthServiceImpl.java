package com.ncedu.cheetahtest.service.security;

import com.ncedu.cheetahtest.config.security.jwt.JwtTokenProvider;
import com.ncedu.cheetahtest.dao.user.UserDao;
import com.ncedu.cheetahtest.entity.mail.PasswordDTO;
import com.ncedu.cheetahtest.entity.security.AccessTokenDto;
import com.ncedu.cheetahtest.entity.security.LoginDto;
import com.ncedu.cheetahtest.entity.security.RegisterDto;
import com.ncedu.cheetahtest.entity.user.ResetToken;
import com.ncedu.cheetahtest.entity.user.User;
import com.ncedu.cheetahtest.entity.user.UserRole;
import com.ncedu.cheetahtest.entity.user.UserStatus;
import com.ncedu.cheetahtest.exception.security.BadCredentialsException;
import com.ncedu.cheetahtest.exception.security.UserAlreadyExistsException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Value("${db.users.salt}")
    private String passwordSalt;

    @Override
    @Transactional
    public void register(@Valid RegisterDto registerDto) {

        if (userDao.findUserByEmail(registerDto.getEmail()) != null) {
            throw new UserAlreadyExistsException();
        }

        String passwordWithSalt = registerDto.getPassword() + passwordSalt;

        User newUser = new User();

        newUser.setEmail(registerDto.getEmail());
        newUser.setName(registerDto.getName());
        newUser.setPass(passwordEncoder.encode(passwordWithSalt));
        newUser.setStatus(UserStatus.ACTIVE);
        newUser.setRole(UserRole.valueOf(registerDto.getRole()));

        userDao.createDeveloper(newUser);
    }

    @Override
    public AccessTokenDto login(@Valid LoginDto loginDto) {

        User user = userDao.findUserByEmail(loginDto.getEmail());
        if (user == null) {
            throw new BadCredentialsException();
        }

        String loginDtoPasswordWithSalt = loginDto.getPassword() + passwordSalt;

        if (!passwordEncoder.matches(loginDtoPasswordWithSalt, user.getPass()) || !UserStatus.ACTIVE.equals(user.getStatus())) {
            throw new BadCredentialsException();
        }
        
        String token = jwtTokenProvider.createToken(user);
        userDao.setUserLastRequest(user.getEmail(), new Date());
        return new AccessTokenDto(token);
    }


    @Override
    @Transactional
    public void changeUserPassword(ResetToken resetToken, String password) {

        String encodedPasswordWithSalt = passwordEncoder.encode(password + passwordSalt);
        userDao.changeUserPassword(resetToken, encodedPasswordWithSalt);
    }

    @Override
    @Transactional
    public boolean validatePassword(PasswordDTO passwordDTO) {
        User theUser = userDao.findUserByToken(passwordDTO.getToken());
        String encodedPasswordWithSalt = passwordDTO.getPassword() + passwordSalt;

        return passwordEncoder.matches(encodedPasswordWithSalt, theUser.getPass());
    }

    @Override
    public boolean isAdmin(String jwtToken) {
        String[] splitString = jwtToken.split("\\.");
        String base64EncodedBody = splitString[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        return body.contains(UserRole.ADMIN.toString());
    }
}
