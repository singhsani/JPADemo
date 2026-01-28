package com.jpaimplementation.jpaDemo.JwtConfiguration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private  JwtFilter jwtFilter;

    /**
     * @param http
     * @return
     * @throws Exception
     *
     * Ye Spring Security ko bolta hai:
     * “Is API ke liye login required nahi hai”
     *
     * Matlab:
     * Token ho ya na ho → request ALLOW hogi
     * Controller tak jaane ki permission
     * ❗ Lekin:
     * Filter chain phir bhi chalegi
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/auth/**","/test/**")
                                .permitAll().anyRequest().authenticated()
                )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * @return
     *
     * BCrypt kya hai?
     * 👉 BCrypt ek strong hashing algorithm hai
     * 👉 Ye slow intentionally hota hai (security ke liye)
     * 👉 Har password ke liye random salt use karta hai
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @param configuration
     * @return
     * @throws Exception
     *
     * AuthenticationManager kya hota hai? (ROOT)
     * 👉 AuthenticationManager = Login ka decision maker
     * Simple words:
     * “Username + password sahi hai ya nahi?”
     * Ye kaam AuthenticationManager karta hai.
     *
     * AuthenticationConfiguration
     * Isme already configured hota hai:
     * UserDetailsService
     * PasswordEncoder
     * AuthenticationProvider
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    }
