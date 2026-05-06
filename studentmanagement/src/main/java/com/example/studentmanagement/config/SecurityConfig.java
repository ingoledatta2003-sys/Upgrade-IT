package com.example.studentmanagement.config;

import org.springframework.context.annotation.Bean;
import com.example.studentmanagement.service.CustomUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class SecurityConfig {
	
	@Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) // optional for now

            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/", 
                        "/login",
                        "/dashboard",
                        "/students",
                        "/courses/**",
                        "/register",
                        "/registered-students",
                        "/saveStudent",
                        "/deleteStudent/**",
                        "/saveRegisteredStudent",
                        "/course-schedule"
                        
                ).permitAll()

                .anyRequest().authenticated()
            )
            
            .userDetailsService(customUserDetailsService)

            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .permitAll()
            )

            .logout(logout -> logout.permitAll());

        return http.build();
    }
}