package com.floorballmanager.floorballteammanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpMethod;
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // USER voi tarkastella listauksia, ADMIN voi käyttää kaikkia toimintoja
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(PathRequest.toH2Console()).hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/players", "/teams", "/teams/view/*")
                .hasAnyRole("USER", "ADMIN")
                .anyRequest().hasRole("ADMIN"))
                // H2-konsolin pyynnöt ohitetaan CSRF-tarkistuksessa
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(PathRequest.toH2Console()))
                // Sallitaan H2-konsolin näyttäminen saman sivuston kehyksessä
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin()))
                .formLogin(form -> form
                        .defaultSuccessUrl("/players", true)
                        .permitAll())
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Luodaan USER- ja ADMIN-käyttäjät muistiin.
    // Salasanat haetaan ympäristömuuttujista.
    @Bean
    public UserDetailsService userDetailsService(
            @Value("${USER_PASSWORD}") String userPassword,
            @Value("${ADMIN_PASSWORD}") String adminPassword,
            PasswordEncoder passwordEncoder) {

        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode(userPassword))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode(adminPassword))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}