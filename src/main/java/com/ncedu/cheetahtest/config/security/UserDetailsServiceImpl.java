package com.ncedu.cheetahtest.config.security;

import com.ncedu.cheetahtest.entity.user.User;
import com.ncedu.cheetahtest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);
        return UserDetailsImpl.fromUserEntityToCustomUserDetails(user);
    }

    public void setUserLastRequest(String email, Date date){ userService.setUserLastRequest(email, date);}
}
