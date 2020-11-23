package com.ncedu.cheetahtest.controller.security;

import com.ncedu.cheetahtest.entity.mail.Email;
import com.ncedu.cheetahtest.entity.security.AccessTokenDto;
import com.ncedu.cheetahtest.entity.security.LoginDto;
import com.ncedu.cheetahtest.entity.security.RegisterDto;
import com.ncedu.cheetahtest.entity.security.RegisterResponse;
import com.ncedu.cheetahtest.entity.user.User;
import com.ncedu.cheetahtest.service.mail.EmailService;
import com.ncedu.cheetahtest.service.security.AuthService;
import com.ncedu.cheetahtest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    public static final String SUBJECT = "Password reset";
    public static final String FRONT_URL = "http://localhost:4200/register?token=";
    private static final String HTML_PATH = "src/main/resources/mail/email.html";

    private AuthService authService;
    private UserService userService;
    private EmailService emailService;

    @Autowired
    public AuthController(AuthService authService, UserService userService, EmailService emailService) {
        this.authService = authService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterDto registerDto) {
        authService.register(registerDto);
        User user = userService.findUserByEmail(registerDto.getEmail());
        String token = UUID.randomUUID().toString();

        userService.createPasswordResetTokenForUser(user, token);
        emailService.sendMessageWithAttachment(registerDto.getEmail(), constructUrl(token), SUBJECT, HTML_PATH);

        return ResponseEntity.ok(new RegisterResponse("success"));
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenDto> login(@RequestBody LoginDto loginDto) {
        AccessTokenDto accessTokenDto = authService.login(loginDto);

        return  ResponseEntity.ok(accessTokenDto);
    }

    private String constructUrl(String token) {
        return FRONT_URL + token;
    }
}
