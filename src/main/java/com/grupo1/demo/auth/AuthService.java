package com.grupo1.demo.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.grupo1.demo.jwt.JwtService;
import com.grupo1.demo.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        // Autenticación del usuario
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        
        // Obtener detalles del usuario
        UserDetails user = usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        
        // Generar el token
        String token = jwtService.getToken(user);

        // Obtener el rol del usuario (suponiendo que UserDetails tiene el rol)
        String role = user.getAuthorities().stream()
                          .map(authority -> authority.getAuthority())
                          .findFirst()
                          .orElse("user"); // Valor por defecto

        // Construir la respuesta
        return AuthResponse.builder()
                           .token(token)
                           .role(role)
                           .build();
    }
}
