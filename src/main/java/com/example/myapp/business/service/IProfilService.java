package com.example.myapp.business.service;

import com.example.myapp.persistence.model.Profil;

import java.util.List;

public interface IProfilService {
    public List<Profil> getListProfil();
    public Profil getProfilById(Long id);
    public Profil addProfil(Profil profil);
    public void deleteProfil(Long id);
    public Profil updateProfil(Profil profil,Long id);
}
