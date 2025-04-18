package org.example.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("GET", "/api/v1/specialities", "/api/v1/specialities/{id}").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers("POST", "PUT", "DELETE", "/api/v1/specialities/**").hasRole("ADMIN")
                        .requestMatchers("GET", "/api/v1/doctors/visits/count").hasAnyRole("ADMIN", "DOCTOR")// Visit count per doctor
                        .requestMatchers("GET", "/api/v1/doctors", "/api/v1/doctors/{id}").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers("POST", "PUT", "DELETE", "/api/v1/doctors/**").hasRole("ADMIN")
                        .requestMatchers("GET", "/api/v1/diagnoses/last").hasAnyRole("ADMIN","DOCTOR")// Most frequent illness
                        .requestMatchers("GET", "/api/v1/diagnoses", "/api/v1/diagnoses/{id}").hasAnyRole("ADMIN","DOCTOR")
                        .requestMatchers("POST", "PUT", "DELETE", "/api/v1/diagnoses/**").hasRole("ADMIN")
                        .requestMatchers("GET", "/api/v1/general-practitioners/patients/count").hasAnyRole("ADMIN", "DOCTOR") //Patient count per gp
                        .requestMatchers("GET", "/api/v1/general-practitioners", "/api/v1/general-practitioners/{id}").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers("POST", "PUT", "DELETE", "/api/v1/general-practitioners/**").hasRole("ADMIN")
                        .requestMatchers("GET", "/api/v1/patients/general-practitioners/{id}").hasAnyRole("ADMIN", "DOCTOR") //Patients under specific Gp
                        .requestMatchers("GET", "/api/v1/patients/diagnoses/{id}").hasAnyRole("ADMIN", "DOCTOR") // Patient with specific diagnose
                        .requestMatchers("GET", "/api/v1/patients", "/api/v1/patients/{id}").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers("POST", "PUT", "DELETE", "/api/v1/patients/**").hasRole("ADMIN")
                        .requestMatchers("GET", "/api/v1/medical-visits/period").hasAnyRole("ADMIN", "DOCTOR")// Visit history of a given period
                        .requestMatchers("GET", "/api/v1/medical-visits/doctors/most/sick-leaves").hasAnyRole("ADMIN", "DOCTOR")// Doctor who issued most sick leaves
                        .requestMatchers("GET", "/api/v1/medical-visits/patients/history").hasRole("PATIENT")//Patient history
                        .requestMatchers("GET", "/api/v1/medical-visits", "/api/v1/medical-visits/{id}").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers("POST", "PUT", "DELETE", "/api/v1/medical-visits/**").hasRole("ADMIN")
                        .requestMatchers("GET", "/api/v1/sick-leaves/month/high").hasAnyRole("ADMIN", "DOCTOR")// Month with highest sick leaves
                        .requestMatchers("GET", "/api/v1/sick-leaves", "/api/v1/sick-leaves/{id}").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers("POST", "PUT", "DELETE", "/api/v1/sick-leaves/**").hasRole("ADMIN")







                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(List.of(provider));
    }




}