package com.aviones.proyectofinal.payload.response;

import com.aviones.proyectofinal.models.EReaction;

public class ReactionCountResponse {
    
    private String reactionType;
    private Long count;

    
    public ReactionCountResponse(String reactionType, Long count) {
        this.reactionType = reactionType;
        this.count = count;
    }


    public String getReactionType() {
        return reactionType;
    }


    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
    }


    public Long getCount() {
        return count;
    }


    public void setCount(Long count) {
        this.count = count;
    }

    
    
}
