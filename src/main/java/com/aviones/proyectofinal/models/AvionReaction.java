package com.aviones.proyectofinal.models;

import jakarta.persistence.*;


@Entity
@Table(name = "avion_reactions", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "avion_id"}),
} )
public class AvionReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reaction_id")
    Long reactionId;

    public Long getReactionId() {
        return reactionId;
    }

    public void setReactionId(Long reactionId) {
        this.reactionId = reactionId;
    }

    @Column(name = "user_id")
    Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "avion_id")
    Long avionId;

    public Long getAvion_id() {
        return avionId;
    }

    public void setAvion_id(Long avion_id) {
        this.avionId = avion_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.userId = user.getId();
        this.user = user;
    }

    @ManyToOne
    @MapsId("avionId")
    @JoinColumn(name = "avion_id")
    Avion avion;

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avionId = avion.getId();
        this.avion = avion;
    }

    @ManyToOne
    @MapsId("reactionId")
    @JoinColumn(name = "reaction_id")
    Reaction reaction;

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reactionId = reaction.getId();
        this.reaction = reaction;
    }
    
}
