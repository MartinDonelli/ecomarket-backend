package com.ecomarket.backend.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarket.backend.model.Usuario;
import com.ecomarket.backend.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String nombreUsuario = body.get("nombreUsuario");
        String password = body.get("password");

        Optional<Usuario> usuario = usuarioRepository.findByNombreUsuario(nombreUsuario);

        if (usuario.isPresent() && usuario.get().getPassword().equals(password)) {
            return ResponseEntity.ok(Map.of(
                "mensaje", "Login exitoso",
                "nombreUsuario", nombreUsuario
            ));
        }

        return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas"));
    }
}
