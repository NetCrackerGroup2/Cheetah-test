package com.ncedu.cheetahtest.config.security;

import com.ncedu.cheetahtest.entity.user.User;
import com.ncedu.cheetahtest.entity.user.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class UserDetailsImpl implements UserDetails {

    private String email;
    private String password;
    private UserStatus status;
    private Collection<? extends GrantedAuthority> grantedAuthorities;
    private Date lastRequest;

    public static UserDetailsImpl fromUserEntityToCustomUserDetails(User userEntity) {
        UserDetailsImpl user = new UserDetailsImpl();
        user.email = userEntity.getEmail();
        user.password = userEntity.getPass();
        user.status = userEntity.getStatus();
        user.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRole().toString()));
        user.lastRequest = userEntity.getLastRequest();
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
        return UserStatus.ACTIVE.equals(status);
    }

    public Date getLastRequest() {
        return lastRequest;
    }
}
