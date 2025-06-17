package com.aviones.proyectofinal.controllers;

import com.aviones.proyectofinal.models.Avion;
import com.aviones.proyectofinal.models.Comentario;
import com.aviones.proyectofinal.models.User;
import com.aviones.proyectofinal.payload.request.ComentarioRequest;
import com.aviones.proyectofinal.payload.response.ComentarioResponse;
import com.aviones.proyectofinal.repository.AvionRepository;
import com.aviones.proyectofinal.repository.ComentarioRepository;
import com.aviones.proyectofinal.repository.UserRepository;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {
    
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private AvionRepository avionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public Page<Comentario> getAllComentarios(Pageable pageable) {
        return comentarioRepository.findAll(pageable);
    }

    @GetMapping("/get/{avionId}")
    public ResponseEntity<List<ComentarioResponse>> getComentariosPorAvion(@PathVariable Long avionId) {
        List<Comentario> comentarios = comentarioRepository.findByAvionId(avionId);

        List<ComentarioResponse> respuesta = comentarios.stream().map(c -> new ComentarioResponse(
                c.getId(),
                c.getContenido(),
                c.getUsuario().getUsername()
        )).toList();

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/create")
    public Comentario createComentario(@Valid @RequestBody ComentarioRequest comentarioRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = getValidUser(username);
        Avion avion = getValidAvion(comentarioRequest.getAvionId());

        Comentario comentario = new Comentario();
        comentario.setContenido(comentarioRequest.getContenido());
        comentario.setAvion(avion);
        comentario.setUsuario(user);

        return comentarioRepository.save(comentario);
    }

    private User getValidUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Avion getValidAvion(Long avionId) {
        return avionRepository.findById(avionId)
                .orElseThrow(() -> new RuntimeException("Avion no encontrado"));
    }
}
