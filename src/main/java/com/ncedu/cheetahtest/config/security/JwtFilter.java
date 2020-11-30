package com.ncedu.cheetahtest.config.security;

import com.ncedu.cheetahtest.config.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@Component
public class JwtFilter extends GenericFilterBean {

    private static final int SESSION_TIMEOUT_HOURS = 1;

    private JwtTokenProvider jwtTokenProvider;

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public JwtFilter(JwtTokenProvider jwtTokenProvider, UserDetailsServiceImpl userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String token = getTokenFromHeader(httpServletRequest);

        if (token != null && jwtTokenProvider.isTokenValid(token)) {
            String userEmail = jwtTokenProvider.getEmail(token);
            UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(userEmail);

            if (isSessionTimedOut(userDetails.getLastRequest())) {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session timed out");
                return;
            }

            userDetailsService.setUserLastRequest(userEmail, new Date());

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String initialToken = request.getHeader("Authorization");

        if (initialToken != null && initialToken.startsWith("Bearer ")) {
            return initialToken.substring(7);
        }
        return null;
    }

    private boolean isSessionTimedOut(Date lastRequest) {

        if (lastRequest == null) {
            return true;
        }

        Calendar sessionTimeout = Calendar.getInstance();
        sessionTimeout.setTime(lastRequest);
        sessionTimeout.add(Calendar.HOUR, SESSION_TIMEOUT_HOURS);

        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTime(new Date());

        return sessionTimeout.before(calendarCurrent);
    }
}

