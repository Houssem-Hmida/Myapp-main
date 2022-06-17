package com.example.myapp.business.service.impl;


import com.example.myapp.business.service.IDemandeService;
import com.example.myapp.persistence.model.Demande;
import com.example.myapp.persistence.repository.DemandeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

import java.util.Date;
import java.util.List;

@Transactional
@AllArgsConstructor
@Service
@Slf4j
public class DemandeService implements IDemandeService {
    public final DemandeRepository demandeRepository;

    @Override
    public List<Demande> getListDemande() {
        try {
            log.info("Fetching all demands ");
            return demandeRepository.findAllByOrderByIdAsc();
        } catch (Exception e){
            throw new IllegalStateException("Error DemandeService in method getListDemande :: " + e.toString());

        }
    }




    @Override
    public Demande getDemandeById(Long id){
        try {
            if (id == null)
                return new Demande();
            Demande d = demandeRepository.findDemandeById(id);
            if (d == null)
                return new Demande();
            log.info("Fetching demand with id :{} ",id);
            return d;
        } catch (Exception e){
            throw new IllegalStateException("Error DemandeService in method getDemandeById :: " + e.toString());
        }

    }


    @Override
    public Demande addDemande(Demande dem) {
        try {
            Demande objNomUnique = demandeRepository.findDemandeBytitle(dem.getTitle());

                dem.setCreationdate(new Timestamp(new Date().getTime()));
            log.info("Saving new demade {} to the databse ",dem.getTitle());
            return demandeRepository.save(dem);
        } catch (Exception e) {
            throw new IllegalStateException("Error DemandeService in method addDemande :: " + e.toString());
        }
    }

    @Override
    public Demande updateDemandeById(Demande demande,Long id) {
        try {
            Demande updem = demandeRepository.findDemandeById(id);
            updem.setTitle(demande.getTitle());
            updem.setDescription(demande.getDescription());
            updem.setDatedebut(demande.getDatedebut());
            updem.setDatefin(demande.getDatefin());
            updem.setUpdatedate(new Timestamp(new Date().getTime()));
            updem.setId(id);
            updem.setEmpid(demande.getEmpid());
            updem.setEtat(demande.getEtat());
            updem.setPriorite(demande.getPriorite());
            log.info("updating demande {} to the database ",demande.getTitle());

            return demandeRepository.save(updem);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error DemandeService in method updateDemandeById :: " + e.toString());
        }
    }

    @Override
    public void deleteDemandeById(Long id) {
        try {
            log.info("Deleting demand with id {}  ",id);
            demandeRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error DemandeService in method deleteDemandeById :: " + e.toString());
        }
    }
}
