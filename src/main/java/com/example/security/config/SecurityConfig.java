package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final DataSource securityDataSource;

    @Autowired
    public SecurityConfig(DataSource securityDataSource) {
        this.securityDataSource = securityDataSource;
    }

    public UserDetailsManager userDetailsManager(){
        return new JdbcUserDetailsManager(securityDataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        return httpSecurity
                .authorizeRequests(configurer -> configurer
                        .antMatchers("/").hasAnyRole("EMPLOYEE")
                        .antMatchers("/moderator/**").hasAnyRole("MANAGER")
                        .antMatchers("/system/**").hasRole("ADMIN"))

                .formLogin(configurer -> configurer
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll())

                .exceptionHandling(configurer -> configurer
                        .accessDeniedPage("/access-denied"))

                .logout(LogoutConfigurer::permitAll)

                .build();


    }
}
