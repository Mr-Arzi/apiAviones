package com.aviones.proyectofinal.payload.response;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;


    public JwtResponse(String accesstoken, Long id, String username, String email, List<String> roles) {
        this.token = accesstoken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }


    public String getAccessToken() {
        return token;
    }


    public void setAccessToken(String accesstoken) {
        this.token = accesstoken;
    }


    public String getTokenType() {
        return type;
    }


    public void setTokenType(String tokentype) {
        this.type = tokentype;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public List<String> getRoles() {
        return roles;
    }


    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    
}
