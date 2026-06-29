package com.ecomarket.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomarket.backend.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}

