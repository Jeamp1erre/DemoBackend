package com.grupo1.demo.controllers;

import com.grupo1.demo.models.Usuario;
import com.grupo1.demo.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // Solo ADMIN puede crear usuarios
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario) {
        Usuario createdUser = usuarioService.createUser(usuario);
        return ResponseEntity.status(201).body(createdUser);
    }

    // Solo ADMIN puede obtener todos los usuarios
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> usuarios = usuarioService.getAllUsers();
        return ResponseEntity.ok(usuarios);
    }

    // Solo ADMIN puede obtener un usuario por ID
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUserById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ResponseEntity.ok(usuario);
    }

    // Solo ADMIN puede actualizar usuarios
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> adminUpdateUser(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Usuario updatedUser = usuarioService.adminUpdateUser(id, usuarioDetails);
        return ResponseEntity.ok(updatedUser);
    }

    // Solo ADMIN puede eliminar usuarios
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Permite que los usuarios se actualicen a sí mismos
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("/me")
    public ResponseEntity<Usuario> updateSelf(@AuthenticationPrincipal Usuario currentUser, @RequestBody Usuario usuarioDetails) {
        if (usuarioDetails.getRole() != null || usuarioDetails.getUsername() != null) {
            throw new RuntimeException("No se puede modificar el rol o el nombre de usuario.");
        }
        Usuario updatedUser = usuarioService.updateUser(currentUser.getId(), usuarioDetails);
        return ResponseEntity.ok(updatedUser);
    }

    // Permite que los usuarios obtengan su propio perfil
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/me")
    public ResponseEntity<Usuario> getCurrentUser(@AuthenticationPrincipal Usuario currentUser) {
        return ResponseEntity.ok(currentUser);
    }
}
