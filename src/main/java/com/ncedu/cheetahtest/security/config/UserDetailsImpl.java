package com.ncedu.cheetahtest.security.config;

import com.ncedu.cheetahtest.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private String email;
    private String password;
    private String status;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static UserDetailsImpl fromUserEntityToCustomUserDetails(User userEntity) {
        UserDetailsImpl user = new UserDetailsImpl();
        user.email = userEntity.getEmail();
        user.password = userEntity.getPass();
        user.status = userEntity.getStatus();
        user.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRole()));
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status.equals("active");
    }
}
