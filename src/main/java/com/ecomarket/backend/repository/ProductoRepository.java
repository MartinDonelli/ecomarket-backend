package com.ecomarket.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomarket.backend.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

