package com.ecomarket.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarket.backend.model.Orden;
import com.ecomarket.backend.service.OrdenService;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @PostMapping
    public ResponseEntity<Orden> confirmar(@RequestBody Map<String, Object> body) {
        Long carritoId = Long.valueOf(body.get("carritoId").toString());
        String usuarioNombre = (String) body.get("usuarioNombre");
        String mensaje = body.containsKey("mensaje") ? (String) body.get("mensaje") : "";
        return ResponseEntity.status(201).body(
            ordenService.confirmar(carritoId, usuarioNombre, mensaje)
        );
    }

    @GetMapping
    public ResponseEntity<List<Orden>> listar() {
        return ResponseEntity.ok(ordenService.listarTodas());
    }
}

