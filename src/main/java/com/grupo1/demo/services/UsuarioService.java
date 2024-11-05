package com.grupo1.demo.services;

import com.grupo1.demo.models.Role;
import com.grupo1.demo.models.Usuario;
import com.grupo1.demo.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario createUser(Usuario usuario) {
        // Verificar si el nombre de usuario ya existe
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        // Asignar rol por defecto si no se proporciona
        if (usuario.getRole() == null) {
            usuario.setRole(Role.USER);
        }

        // Codificar la contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAllByOrderByIdDesc();
    }

    public Optional<Usuario> getUserById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario updateUser(Long id, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
        // Actualizar solo los campos permitidos
        if (usuarioDetails.getFirstname() != null) {
            usuario.setFirstname(usuarioDetails.getFirstname());
        }
        if (usuarioDetails.getLastname() != null) {
            usuario.setLastname(usuarioDetails.getLastname());
        }
        if (usuarioDetails.getEmail() != null) {
            usuario.setEmail(usuarioDetails.getEmail());
        }
        if (usuarioDetails.getPhone() != null) {
            usuario.setPhone(usuarioDetails.getPhone());
        }
        if (usuarioDetails.getPassword() != null) {
            usuario.setPassword(passwordEncoder.encode(usuarioDetails.getPassword())); // Asegúrate de codificar la contraseña
        }
    
        return usuarioRepository.save(usuario);
    }
    

    public void deleteUser(Long id) {
        Usuario existingUsuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioRepository.delete(existingUsuario);
    }

    public Usuario adminUpdateUser(Long id, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
        // Actualizar todos los campos
        if (usuarioDetails.getFirstname() != null) {
            usuario.setFirstname(usuarioDetails.getFirstname());
        }
        if (usuarioDetails.getLastname() != null) {
            usuario.setLastname(usuarioDetails.getLastname());
        }
        if (usuarioDetails.getEmail() != null) {
            usuario.setEmail(usuarioDetails.getEmail());
        }
        if (usuarioDetails.getPhone() != null) {
            usuario.setPhone(usuarioDetails.getPhone());
        }
        if (usuarioDetails.getUsername() != null) {
            usuario.setUsername(usuarioDetails.getUsername());
        }
        if (usuarioDetails.getRole() != null) {
            usuario.setRole(usuarioDetails.getRole());
        }
        if (usuarioDetails.getPassword() != null) {
            usuario.setPassword(passwordEncoder.encode(usuarioDetails.getPassword())); // Asegúrate de codificar la contraseña
        }
    
        return usuarioRepository.save(usuario);
    }
    
}
