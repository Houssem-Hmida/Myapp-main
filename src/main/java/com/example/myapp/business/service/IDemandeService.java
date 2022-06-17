package com.example.myapp.business.service;

import com.example.myapp.persistence.model.Demande;

import java.util.List;

public interface IDemandeService {

    public List<Demande> getListDemande();
    public Demande getDemandeById(Long id);
    public Demande addDemande(Demande dem);

    Demande updateDemandeById(Demande demande, Long id);

    public void deleteDemandeById(Long id);


}
