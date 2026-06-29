package com.ecomarket.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomarket.backend.model.ItemCarrito;

public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, Long> {
    Optional<ItemCarrito> findByCarritoIdAndProductoId(Long carritoId, Long productoId);
}
