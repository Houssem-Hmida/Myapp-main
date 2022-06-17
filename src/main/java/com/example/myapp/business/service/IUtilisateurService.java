package com.example.myapp.business.service;

import com.example.myapp.persistence.model.Profil;
import com.example.myapp.persistence.model.Utilisateur;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IUtilisateurService {
    public List<Utilisateur> getListUtilisateur();
    public Utilisateur addUtilisateur(Utilisateur obj);
    public void deleteUtilisateur(Long id);
    public Utilisateur updateUtilisateur(Utilisateur utilisateur,Long id);

    Set<Profil> getListUtilisateurProfils(Long id);

    public Utilisateur getUtilisateurById(Long id);

    Set<Profil> getUtilisateurRoleByEmpId(Long id);

    Utilisateur getUtilisateurByuserName(String userName);


    Utilisateur findUtilisateurByEmail(String email);


    ResponseEntity<String> changePassword(Map<String, String> requestMap);

    String currentUserName();

    void updateResetPasswordToken(String token, String email);

    Utilisateur getByResetPasswordToken(String token);

    void updatePassword(Utilisateur utilisateur, String newPassword);
/*
    Utilisateur connect(Utilisateur user);

    Utilisateur disconnect(Utilisateur user);
    */

}
