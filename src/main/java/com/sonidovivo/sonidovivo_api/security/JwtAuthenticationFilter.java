package com.sonidovivo.sonidovivo_api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
//heredamos de OncePerRequest, significa que se ejecuta una vez por cada peticion http

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //lee la cabecera authorization de la peticion
        String authHeader = request.getHeader("Authorization");

        //revisa si empieza con Bearer y recorta los primeros 7 caracteres para quedarse solo con el token limpio
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

        //le pregunta a jwtcService si es legitimo
            if (jwtService.esTokenValido(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                String correo = jwtService.extraerCorreo(token);

        //si es valido, crea un pase interno de spring security (usernamePasswordAuthenticationToken) con el correo del usuario
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(correo, null, Collections.emptyList());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        //se guarda en securityContextHolder. asi spring sabe que esa peticion viene autenticada
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}