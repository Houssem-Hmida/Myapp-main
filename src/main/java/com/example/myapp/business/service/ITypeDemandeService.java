package com.example.myapp.business.service;

import com.example.myapp.persistence.model.TypeDemande;

import java.util.List;

public interface ITypeDemandeService {
    public List<TypeDemande> getListTypeDemande();
    public TypeDemande getTypeDemandeById(Long id);
}
