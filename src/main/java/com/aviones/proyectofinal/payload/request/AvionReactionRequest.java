package com.aviones.proyectofinal.payload.request;

import jakarta.validation.constraints.NotBlank;

public class AvionReactionRequest {

    private Long avionId;
    private Long reactionId;


    public Long getAvionId() {
        return avionId;
    }
    public void setAvionId(Long avionId) {
        this.avionId = avionId;
    }
    public Long getReactionId() {
        return reactionId;
    }
    public void setReactionId(Long reactionId) {
        this.reactionId = reactionId;
    }

    
    
}
