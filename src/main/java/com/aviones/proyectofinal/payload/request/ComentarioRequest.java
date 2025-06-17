package com.aviones.proyectofinal.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ComentarioRequest {
    
    @NotNull
    private Long avionId;

    @NotBlank
    private String contenido;

    
   
    
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public Long getAvionId() {
        return avionId;
    }
    public void setAvionId(Long avionId) {
        this.avionId = avionId;
    }

    
}
