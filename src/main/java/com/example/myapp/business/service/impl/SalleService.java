package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.ISalleService;
import com.example.myapp.persistence.model.Salle;
import com.example.myapp.persistence.repository.SalleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class SalleService implements ISalleService {
    public final SalleRepository salleRepository;


    @Override
    public List<Salle> getListSalle() {
        try {
            log.info("Fetching all salles ");
            return salleRepository.findAllByOrderByIdAsc();
        } catch (Exception e){
            throw new IllegalStateException("Error SalleService in method getListSalle :: " + e.toString());

        }
    }

    @Override
    public Salle getSalleById(Long id){
        try {
            if (id == null)
                return new Salle();
            Salle s = salleRepository.findSalleById(id);
            if (s == null)
                return new Salle();
            log.info("Fetching salle with id :{} ",id);
            return s;
        } catch (Exception e){
            throw new IllegalStateException("Error SalleService in method getSalleById :: " + e.toString());
        }

    }


    @Override
    public Salle addSalle(Salle salle) {
        try {
            Salle objNomUnique = salleRepository.findSalleByNom(salle.getNom());

            if ( objNomUnique != null)
                throw new IllegalStateException("Salle name token");

            salle.setDatecreation(new Timestamp(new Date().getTime()));
            log.info("Saving new salle {} to the databse ",salle.getNom());
            return salleRepository.save(salle);
        } catch (Exception e) {
            throw new IllegalStateException("Error SalleService in method addSalle :: " + e.toString());
        }
    }
    @Override
    public Salle updateSalle(Salle salle,Long id) {
        try {
            Salle ups = salleRepository.findSalleById(salle.getId());
            ups.setNom(salle.getNom());
            ups.setNbposte(salle.getNbposte());
            ups.setType(salle.getType());
            ups.setDep(salle.getDep());
            ups.setPourcentagePres(salle.getPourcentagePres());
            ups.setDateupdate(new Timestamp(new Date().getTime()));
            ups.setId(id);
            log.info("updating salle {} to the database ",salle.getNom());
            return salleRepository.save(ups);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error SalleService in method updateSalle :: " + e.toString());
        }
    }

    @Override
    public void deleteSalle(Long id) {
        try {
            log.info("Deleting salle with id {}  ",id);
            salleRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error SalleService in method deleteSalle :: " + e.toString());
        }
    }
}
