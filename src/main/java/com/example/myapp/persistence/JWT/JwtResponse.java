package com.example.myapp.persistence.JWT;

import com.example.myapp.persistence.model.Utilisateur;

public class JwtResponse {

    private Utilisateur utilisateur;
    private String jwtToken;

    public JwtResponse(Utilisateur utilisateur, String jwtToken) {
        this.utilisateur = utilisateur;
        this.jwtToken = jwtToken;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}