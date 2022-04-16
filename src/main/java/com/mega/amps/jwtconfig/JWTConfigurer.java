package com.mega.amps.jwtconfig;

import com.mega.amps.service.UserSevice;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    private final TokenProvider tokenProvider;
    private final UserSevice userSevice;

    public JWTConfigurer(TokenProvider tokenProvider, UserSevice userSevice){

        this.tokenProvider = tokenProvider;
        this.userSevice = userSevice;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        JWTFilter customFilter = new JWTFilter(tokenProvider, userSevice);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);

    }

}
