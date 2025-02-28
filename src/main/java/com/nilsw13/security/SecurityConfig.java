package com.nilsw13.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


//    ceci est une demo , la securité est vraiment basique (inexsitante car les routes qui interferes avec de la data sont ouverte)
//        cpeendant cette classe est la pour montré comment on devrais implémenté la securité par la suite avec un jwt token avc une durée de vie limité ou autre
//            ici on va juste mettre un header statique attendu dans les requetes depuis le frontend mais en soit on peux retrouvé ces header depuis le frontend donc pas trop securisé mais cest pour montré l'esprit

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/v1/transactions").permitAll()
                        .requestMatchers("/v1/add-transaction").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .permitAll()
                );

        return http.build();
    }


    @Bean

}
