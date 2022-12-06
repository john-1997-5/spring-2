package com.johnson.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .ignoringAntMatchers("/saveMsg") // desactiva csrf para este endpoint
                .ignoringAntMatchers("/public/**")
                .and()
                .authorizeHttpRequests(auth -> {
                    try {
                        auth
                                .antMatchers("/login").permitAll()
                                .antMatchers("/displayMessages").hasRole("ADMIN")
                                .antMatchers("/dashboard").authenticated()
                                .antMatchers("/home").permitAll()
                                .antMatchers("/holidays/**").permitAll()
                                .antMatchers("/contact").permitAll()
                                .antMatchers("/saveMsg").permitAll()
                                .antMatchers("/courses").authenticated() // necesita autenticarse
                                .antMatchers("/about").permitAll()
                                .antMatchers("/public/**").permitAll()
                                .and().formLogin().loginPage("/login")
                                .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
                                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                                .and().httpBasic();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
