package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IEtatDemandeService;
import com.example.myapp.persistence.model.EtatDemande;
import com.example.myapp.persistence.repository.EtatDemandeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class EtatDemandeService implements IEtatDemandeService {

    EtatDemandeRepository etatDemandeRepository;

    @Override
    public List<EtatDemande> getListEtatDemande() {
        try {
            return etatDemandeRepository.findAll();
        } catch (Exception e){
            throw new IllegalStateException("Error EtatDemandeService in method getListEtatDemande :: " + e.toString());

        }
    }

    @Override
    public EtatDemande getEtatDemandeById(Long id){
        try {
            if (id == null)
                return new EtatDemande();
            EtatDemande e = etatDemandeRepository.findEtatDemandeById(id);
            if (e == null)
                return new EtatDemande();
            return e;
        } catch (Exception e){
            throw new IllegalStateException("Error EtatDemandeService in method getEtatDemandeById :: " + e.toString());
        }

    }
}
