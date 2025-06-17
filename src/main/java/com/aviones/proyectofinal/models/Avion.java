package com.aviones.proyectofinal.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "avion")
public class Avion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 140)
    private String nombre;

    @NotBlank
    @Size(max = 100)
    private String modelo;

    @NotBlank
    @Size(max = 255)
    private String urlFoto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posted_by", referencedColumnName = "id")
    private User postedBy;

    @JsonIgnore
    @OneToMany(mappedBy = "avion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comentario> comentarios = new HashSet<>();
    


    public Avion(){

    }



    public Avion( String nombre, String modelo, String urlFoto) {
        this.nombre = nombre;
        this.modelo = modelo;
        this.urlFoto = urlFoto;
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getNombre() {
        return nombre;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getModelo() {
        return modelo;
    }



    public void setModelo(String modelo) {
        this.modelo = modelo;
    }



    public String getUrlFoto() {
        return urlFoto;
    }



    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }



    public User getPostedBy() {
        return postedBy;
    }



    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }



    public Set<Comentario> getComentarios() {
        return comentarios;
    }



    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    
}
