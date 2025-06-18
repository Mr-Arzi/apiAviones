package com.aviones.proyectofinal.controllers;

import java.util.Optional;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aviones.proyectofinal.models.Avion;
import com.aviones.proyectofinal.models.User;

import com.aviones.proyectofinal.repository.AvionRepository;
import com.aviones.proyectofinal.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/avion")
public class AvionController {
    @Autowired
    private AvionRepository avionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public Page<Avion> getAllAviones(Pageable pageable) {
        return avionRepository.findAll(pageable);
    }

    @PostMapping("/create")
    public Avion createAvion(@Valid @RequestBody Avion avion) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        User user = getValidUser(userId);
        Avion newAvion = new Avion(avion.getNombre(), avion.getModelo(), avion.getUrlFoto());
        newAvion.setPostedBy(user);
        avionRepository.save(newAvion);

        return newAvion;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Avion>> buscarAviones(@RequestParam String termino) {
        List<Avion> resultados = avionRepository
            .findByNombreContainingIgnoreCaseOrModeloContainingIgnoreCase(termino, termino);
        return ResponseEntity.ok(resultados);
    }

        @DeleteMapping("/borrar-todo")
    public ResponseEntity<String> borrarTodosLosAviones() {
        avionRepository.deleteAll();
        return ResponseEntity.ok("Todos los aviones han sido borrados.");
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> borrarAvion(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado.");
        User user = userOpt.get();

        Optional<Avion> avionOpt = avionRepository.findById(id);
        if (avionOpt.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Helicóptero no encontrado.");
        Avion avion = avionOpt.get();

        if (!avion.getPostedBy().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permiso para borrar este helicóptero.");
        }

        avionRepository.deleteById(id);
        return ResponseEntity.ok("avion eliminado con éxito.");
    }

    private User getValidUser(String userId) {
        Optional<User> userOpt = userRepository.findByUsername(userId);
        return userOpt.orElseThrow(() -> new RuntimeException("User not found"));
    }

    

}
