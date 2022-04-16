package com.mega.amps.securityconfig;

import com.mega.amps.jwtconfig.JWTConfigurer;
import com.mega.amps.jwtconfig.TokenProvider;
import com.mega.amps.service.UserSevice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final UserSevice userSevice;

    public SecurityConfig(TokenProvider tokenProvider, UserSevice userSevice){

        this.tokenProvider = tokenProvider;
        this.userSevice = userSevice;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/connection").hasRole("USER")
                    .antMatchers("/api/connection/admin").hasRole("ADMIN")
                .antMatchers("/api/register").permitAll()
                .antMatchers("/api/**").authenticated()
                .anyRequest().authenticated()
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().apply(securityConfigurerAdapter());


    }

    public JWTConfigurer securityConfigurerAdapter(){
        return new JWTConfigurer(tokenProvider, userSevice);
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userSevice);
//        authProvider.setAuthoritiesMapper();
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
