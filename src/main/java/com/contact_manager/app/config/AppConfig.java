package com.contact_manager.app.config;

import com.contact_manager.app.model.entities.CustomResponseEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppConfig {

    private final CustomUserDetailsService userDetailsService;

    public AppConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(this.userDetailsService);  // Use injected service
        authenticationProvider.setPasswordEncoder(this.passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public CustomResponseEntity<?> customResponseEntity() {
        return new CustomResponseEntity<>();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> {
                    authz.requestMatchers("/v1/auth/**").permitAll();
                    authz.requestMatchers("/v1/base/**").permitAll();
                    authz.requestMatchers("/v1/user/**").hasRole("USER");
                    // Allow static resources
                    authz.requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "/static/**").permitAll();
                    authz.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/v1/auth/login")
                        .loginProcessingUrl("/auth")
                        .defaultSuccessUrl("/v1/user/dashboard", true)
                        .failureUrl("/v1/auth/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/v1/auth/login")
                        .deleteCookies("JSESSIONID")
                )
                .rememberMe(rememberMe -> rememberMe
                        .key("uniqueAndSecret")
                        .tokenValiditySeconds(86400)
                );

        return http.build();
    }
}
