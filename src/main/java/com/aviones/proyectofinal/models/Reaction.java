package com.aviones.proyectofinal.models;

import jakarta.persistence.*;

@Entity
@Table(name = "reactions")
public class Reaction {
    
    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  //@Enumerated(EnumType.STRING)
  @Column(length = 20)
  private String description;

  public Reaction() {
  }

  public Reaction(String description) {
    this.description = description;
  }

  // getters and setters

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
