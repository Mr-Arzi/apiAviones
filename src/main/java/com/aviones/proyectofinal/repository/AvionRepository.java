package com.aviones.proyectofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aviones.proyectofinal.models.Avion;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Long> {

    List<Avion> findByNombreContainingIgnoreCaseOrModeloContainingIgnoreCase(String nombre, String modelo);
}
