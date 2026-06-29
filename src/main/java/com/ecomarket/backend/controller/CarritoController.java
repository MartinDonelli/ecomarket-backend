package com.ecomarket.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarket.backend.model.ItemCarrito;
import com.ecomarket.backend.service.CarritoService;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping
    public ResponseEntity<?> crearCarrito() {
        return ResponseEntity.status(201).body(carritoService.crearCarrito());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCarrito(@PathVariable Long id) {
        return ResponseEntity.ok(carritoService.obtenerConTotal(id));
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<ItemCarrito> agregarItem(
            @PathVariable Long id,
            @RequestBody Map<String, Object> body) {
        Long productoId = Long.valueOf(body.get("productoId").toString());
        Integer cantidad = Integer.valueOf(body.get("cantidad").toString());
        return ResponseEntity.status(201).body(
            carritoService.agregarItem(id, productoId, cantidad)
        );
    }

    @PutMapping("/{carritoId}/items/{itemId}")
    public ResponseEntity<ItemCarrito> actualizarCantidad(
            @PathVariable Long carritoId,
            @PathVariable Long itemId,
            @RequestBody Map<String, Integer> body) {
        return ResponseEntity.ok(
            carritoService.actualizarCantidad(itemId, body.get("cantidad"))
        );
    }

    @DeleteMapping("/{carritoId}/items/{itemId}")
    public ResponseEntity<Void> eliminarItem(
            @PathVariable Long carritoId,
            @PathVariable Long itemId) {
        carritoService.eliminarItem(itemId);
        return ResponseEntity.noContent().build();
    }
}
