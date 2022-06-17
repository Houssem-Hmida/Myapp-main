package com.example.myapp.business.service;

import com.example.myapp.persistence.model.EtatDemande;

import java.util.List;

public interface IEtatDemandeService {
    public List<EtatDemande> getListEtatDemande();
    public EtatDemande getEtatDemandeById(Long id);
}
