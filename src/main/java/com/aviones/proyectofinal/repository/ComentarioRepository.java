package com.aviones.proyectofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aviones.proyectofinal.models.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByAvionId(Long avionId);
    
    void deleteByAvionId(Long avionId);
}
