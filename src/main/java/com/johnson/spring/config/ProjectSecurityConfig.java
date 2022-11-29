package com.johnson.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // permitir acceso a todas las requests
/*        return http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults())
                .build();*/

        // denegar acceso a todas las requests
/*        return http.authorizeHttpRequests(auth -> auth.anyRequest().denyAll())
                .httpBasic(Customizer.withDefaults())
                .build();*/

        // se permite el acceso a unas páginas, pero para otras se necesita autenticarse

        http.csrf()
                .ignoringAntMatchers("/saveMsg") // desactiva csrf para este endpoint
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
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("steve")
                .password("steve")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("johnson")
                .password("johnson")
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(user, admin);
    }
}
