package com.ecomarket.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomarket.backend.model.Orden;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
}

