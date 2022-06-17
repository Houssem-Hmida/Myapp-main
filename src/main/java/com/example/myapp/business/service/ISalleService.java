package com.example.myapp.business.service;


import com.example.myapp.persistence.model.Salle;

import java.util.List;

public interface ISalleService{
        public List<Salle> getListSalle();
        public Salle addSalle(Salle Salle);
        public void deleteSalle(Long id);
    public Salle updateSalle(Salle Salle,Long id);
    public Salle getSalleById(Long id);

}