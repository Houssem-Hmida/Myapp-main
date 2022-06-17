package com.example.myapp.business.service;


import com.example.myapp.persistence.model.Departement;

import java.util.List;

public interface IDepartementService {
    public List<Departement> getListDepartement();
    public Departement getDepartementById(Long id);

    Departement getDepartementByName(String name);

    public Departement addDepartement(Departement dep);
    public void deleteDepartementById(Long id);
    public Departement updateDepartementById(Departement departement,Long id);
}
