package com.nilsw13.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CustomHeaderFilter extends OncePerRequestFilter {

    private final String expectedToken = "dashboard_secret";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

       String dashBoardToken = request.getHeader("X-Dashboard-Token");
       if (dashBoardToken == null || !dashBoardToken.equals(expectedToken)){
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           response.getWriter().write("Acces non autorisé: Token invalide ou manquant");
           // retourne le status non autorisé
           return;
       }

        // token valide = continue
        filterChain.doFilter(request, response);

    }
}
