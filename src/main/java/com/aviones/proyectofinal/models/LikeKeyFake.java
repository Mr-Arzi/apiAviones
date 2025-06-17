package com.aviones.proyectofinal.models;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
class LikeKeyFake implements Serializable {

    @Column(name = "reaction_id")
    Long reactionId;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "avion_id")
    Long avionId;

    public Long getReactionId() {
        return reactionId;
    }

    public void setReactionId(Long reactionId) {
        this.reactionId = reactionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAvionId() {
        return avionId;
    }

    public void setAvionId(Long avionId) {
        this.avionId = avionId;
    }

    
}
