package com.ncedu.cheetahtest.security.controller;

import com.ncedu.cheetahtest.security.entity.AccessTokenDto;
import com.ncedu.cheetahtest.security.entity.LoginDto;
import com.ncedu.cheetahtest.security.entity.RegisterDto;
import com.ncedu.cheetahtest.security.entity.RegisterResponse;
import com.ncedu.cheetahtest.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterDto registerDto) {
        authService.register(registerDto);

        return ResponseEntity.ok(new RegisterResponse("success"));
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenDto> login(@RequestBody LoginDto loginDto) {
        AccessTokenDto accessTokenDto = authService.login(loginDto);

        return  ResponseEntity.ok(accessTokenDto);
    }
    
}
