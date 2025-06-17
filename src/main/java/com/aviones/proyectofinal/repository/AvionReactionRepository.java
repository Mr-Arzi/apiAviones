package com.aviones.proyectofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aviones.proyectofinal.models.AvionReaction;
import com.aviones.proyectofinal.payload.response.ReactionCountResponse;

@Repository
public interface AvionReactionRepository extends JpaRepository<AvionReaction, Long> {

    @Query("SELECT cr.reaction.description, COUNT(cr) as total " +
            "FROM AvionReaction cr " +
            "WHERE cr.avion.id = :avionId " +
            "GROUP BY cr.reaction.description " +
            "ORDER BY total DESC")
    List<Object[]> findMostVotedReactionByAvionId(@Param("avionId") Long avionId);

    void deleteByAvionId(Long avionId);
}
