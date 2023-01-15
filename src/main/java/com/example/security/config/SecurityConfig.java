package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailManager() {
        UserDetails dominik = User.builder()
                .username("user")
                .password("{noop}user")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin")
                .roles("ADMIN", "MODERATOR", "USER")
                .build();

        UserDetails moderator = User.builder()
                .username("mod")
                .password("{noop}mod")
                .roles("MOD", "USER")
                .build();

        return new InMemoryUserDetailsManager(admin, moderator, dominik);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        return httpSecurity
                .authorizeRequests(configurer -> configurer
                        .antMatchers("/").hasAnyRole("USER", "MOD", "ADMIN")
                        .antMatchers("/moderator/**").hasAnyRole("MOD", "ADMIN")
                        .antMatchers("/system/**").hasRole("ADMIN"))

                .formLogin(configurer -> configurer
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll())

                .logout(LogoutConfigurer::permitAll)

                .exceptionHandling(configurer -> configurer
                        .accessDeniedPage("/access-denied"))

                .build();


    }
}
