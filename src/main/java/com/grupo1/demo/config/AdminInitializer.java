package com.grupo1.demo.config;

import com.grupo1.demo.models.Role;
import com.grupo1.demo.models.Usuario;
import com.grupo1.demo.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminInitializer {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initializeAdmin() {
        return args -> {
            // Verifica si ya existe un usuario con rol ADMIN
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                // Crea el usuario admin si no existe
                Usuario adminUser = Usuario.builder()
                    .firstname("Admin")
                    .lastname("User")
                    .email("admin@example.com")
                    .phone("123456789")
                    .username("admin")
                    .password(passwordEncoder.encode("admin")) // Contraseña codificada
                    .role(Role.ADMIN) // Asigna el rol ADMIN
                    .build();
                
                usuarioRepository.save(adminUser);
                System.out.println("Usuario administrador predeterminado creado con éxito.");
            } else {
                System.out.println("Usuario administrador ya existe.");
            }
        };
    }
}
