package com.taller.castillo.felipe.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/tsscadmin/**", "/api/**")
                .hasAnyRole("admin", "superadmin")
                .antMatchers("/api/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }
}