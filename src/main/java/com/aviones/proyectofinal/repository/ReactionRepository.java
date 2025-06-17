package com.aviones.proyectofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aviones.proyectofinal.models.Reaction;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    
}
