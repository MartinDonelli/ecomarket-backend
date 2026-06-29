package com.ecomarket.backend.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomarket.backend.model.Carrito;
import com.ecomarket.backend.model.EstadoCarrito;
import com.ecomarket.backend.model.ItemCarrito;
import com.ecomarket.backend.model.Producto;
import com.ecomarket.backend.repository.CarritoRepository;
import com.ecomarket.backend.repository.ItemCarritoRepository;
import com.ecomarket.backend.repository.ProductoRepository;

@Service
public class CarritoService {

    @Autowired private CarritoRepository carritoRepository;
    @Autowired private ItemCarritoRepository itemRepository;
    @Autowired private ProductoRepository productoRepository;

    public Carrito crearCarrito() {
        return carritoRepository.save(new Carrito());
    }

    public Carrito buscarPorId(Long id) {
        return carritoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
    }

    public Map<String, Object> obtenerConTotal(Long id) {
        Carrito carrito = buscarPorId(id);
        double total = carrito.getItems().stream()
            .mapToDouble(item -> item.getProducto().getPrecio() * item.getCantidad())
            .sum();
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("carrito", carrito);
        respuesta.put("total", total);
        return respuesta;
    }

    public ItemCarrito agregarItem(Long carritoId, Long productoId, Integer cantidad) {
        Carrito carrito = buscarPorId(carritoId);

        if (carrito.getEstado() == EstadoCarrito.CONFIRMADO) {
            throw new RuntimeException("No se puede modificar un carrito confirmado");
        }

        Producto producto = productoRepository.findById(productoId)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Si el producto ya está en el carrito, suma la cantidad
        Optional<ItemCarrito> existente = itemRepository
            .findByCarritoIdAndProductoId(carritoId, productoId);

        if (existente.isPresent()) {
            ItemCarrito item = existente.get();
            item.setCantidad(item.getCantidad() + cantidad);
            return itemRepository.save(item);
        }

        ItemCarrito nuevo = new ItemCarrito();
        nuevo.setCarrito(carrito);
        nuevo.setProducto(producto);
        nuevo.setCantidad(cantidad);
        return itemRepository.save(nuevo);
    }

    public ItemCarrito actualizarCantidad(Long itemId, Integer cantidad) {
        ItemCarrito item = itemRepository.findById(itemId)
            .orElseThrow(() -> new RuntimeException("Ítem no encontrado"));
        item.setCantidad(cantidad);
        return itemRepository.save(item);
    }

    public void eliminarItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}
