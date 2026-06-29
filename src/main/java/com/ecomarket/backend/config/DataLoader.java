package com.ecomarket.backend.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ecomarket.backend.model.Producto;
import com.ecomarket.backend.model.Usuario;
import com.ecomarket.backend.repository.ProductoRepository;
import com.ecomarket.backend.repository.UsuarioRepository;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired private ProductoRepository productoRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (productoRepository.count() == 0) {
            productoRepository.saveAll(List.of(
                new Producto(null, "Jabón artesanal de lavanda", "100% natural, sin conservantes artificiales", 850.0, 50, "Higiene", "Orgánico certificado"),
                new Producto(null, "Bolsa de tela reutilizable", "Algodón orgánico, reemplaza 500 bolsas de plástico", 1200.0, 100, "Hogar", "Sin plástico"),
                new Producto(null, "Shampoo sólido", "Equivale a 3 botellas de shampoo líquido", 1500.0, 30, "Higiene", "Zero waste"),
                new Producto(null, "Miel pura de colmena", "Producción local, sin aditivos ni conservantes", 2100.0, 25, "Alimentación", "Agroecológico"),
                new Producto(null, "Cepillo de bambú", "Biodegradable al 100%, cerdas de nylon libre de BPA", 650.0, 80, "Higiene", "Biodegradable"),
                new Producto(null, "Vela de soja aromática", "Cera de soja natural, libre de parafina", 900.0, 40, "Hogar", "Vegano")
            ));
        }

        if (usuarioRepository.count() == 0) {
            usuarioRepository.saveAll(List.of(
                new Usuario(null, "admin", "admin123"),
                new Usuario(null, "cliente", "cliente123")
            ));
        }
    }
}
