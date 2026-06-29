package com.ecomarket.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomarket.backend.model.Carrito;
import com.ecomarket.backend.model.EstadoCarrito;
import com.ecomarket.backend.model.Orden;
import com.ecomarket.backend.repository.CarritoRepository;
import com.ecomarket.backend.repository.OrdenRepository;

@Service
public class OrdenService {

    @Autowired private OrdenRepository ordenRepository;
    @Autowired private CarritoRepository carritoRepository;
    @Autowired private CarritoService carritoService;

    public Orden confirmar(Long carritoId, String usuarioNombre, String mensaje) {
        Carrito carrito = carritoService.buscarPorId(carritoId);

        if (carrito.getEstado() == EstadoCarrito.CONFIRMADO) {
            throw new RuntimeException("Este carrito ya fue confirmado");
        }
        if (carrito.getItems().isEmpty()) {
            throw new RuntimeException("No se puede confirmar un carrito vacío");
        }

        double total = carrito.getItems().stream()
            .mapToDouble(item -> item.getProducto().getPrecio() * item.getCantidad())
            .sum();

        carrito.setEstado(EstadoCarrito.CONFIRMADO);
        carritoRepository.save(carrito);

        Orden orden = new Orden();
        orden.setCarritoId(carritoId);
        orden.setUsuarioNombre(usuarioNombre);
        orden.setMensaje(mensaje);
        orden.setFechaConfirmacion(LocalDateTime.now());
        orden.setTotal(total);

        return ordenRepository.save(orden);
    }

    public List<Orden> listarTodas() {
        return ordenRepository.findAll();
    }
}
