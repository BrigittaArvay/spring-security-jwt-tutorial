package com.example.demo.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    // allowed by anyone
                    .antMatchers("/auth/signin").permitAll()
                    // allowed only when signed in
                    .antMatchers(HttpMethod.GET, "/vehicles/**").authenticated()
                    // allowed only if signed in with ADMIN role
                    .antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")
                    // anything else is denied
                    .anyRequest().denyAll();
    }
}
