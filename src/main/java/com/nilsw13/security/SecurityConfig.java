package com.nilsw13.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


// ceci est une demo , la securité est vraiment basique (inexsitante car les routes qui interferes avec de la data sont ouverte)
// cpeendant cette classe est la pour montré comment on devrais implémenté la securité par la suite avec un jwt token avc une durée de vie limité ou autre
// ici on va juste mettre un header statique attendu dans les requetes depuis le frontend mais en soit on peux retrouvé ces header depuis le frontend donc pas trop securisé mais cest pour montré l'esprit


    @Bean
    public CustomHeaderFilter customHeaderFilter(){
        return new CustomHeaderFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomHeaderFilter customHeaderFilter) throws Exception {
        http

                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/v1/transactions").permitAll()
                        .requestMatchers("/v1/add-transaction").permitAll()
                        .requestMatchers("/v1/savings").permitAll()
                        .requestMatchers("/v1/add-saving").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(customHeaderFilter, DisableEncodeUrlFilter.class)
                .formLogin(formLogin -> formLogin
                        .permitAll()
                );

        return http.build();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList(
                "http://localhost:5173"
        ));

        corsConfiguration.setAllowedMethods(Arrays.asList(
                "GET",
                "POST",
                "UPDATE",
                "OPTIONS",
                "PUT",
                "DELETE"
        ));

        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers", "X-dashboard-Token"));

        corsConfiguration.setAllowCredentials(true);

        corsConfiguration.setMaxAge(3600L);


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;

    }




}
