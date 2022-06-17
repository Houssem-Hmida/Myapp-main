package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IDepartementService;
import com.example.myapp.persistence.model.Departement;
import com.example.myapp.persistence.repository.DepartementRepository;
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
public class DepartementService implements IDepartementService {

    public final DepartementRepository departementRepository;

    @Override
    public List<Departement> getListDepartement() {
        try {
            log.info("Fetching all departments ");
            return departementRepository.findAllByOrderByIdAsc();
        } catch (Exception e){
            throw new IllegalStateException("Error DepartementService in method getListDepartement :: " + e.toString());
        }
    }

    @Override
    public Departement getDepartementById(Long id){
        try {
            if (id == null)
                return new Departement();
            Departement d = departementRepository.findDepartementById(id);
            if (d == null)
                return new Departement();
            log.info("Fetching department with id :{} ",id);

            return d;
        } catch (Exception e){
            throw new IllegalStateException("Error DepartementService in method getDepartementById :: " + e.toString());
        }

    }

    @Override
    public Departement getDepartementByName(String name){
        try {
            if (name == null)
                return new Departement();
            Departement d = departementRepository.findDepartementByName(name);
            if (d == null)
                return new Departement();
            log.info("Fetching department with name :{} ",name);

            return d;
        } catch (Exception e){
            throw new IllegalStateException("Error DepartementService in method getDepartementByName :: " + e.toString());
        }

    }

    @Override
    public Departement addDepartement(Departement dep) {
        try {
            Departement depNomUnique = departementRepository.findDepartementByName(dep.getName());

            if ( depNomUnique != null)
                throw new IllegalStateException("Departement name token");

            dep.setCreationdate(new Timestamp(new Date().getTime()));
            dep.setNbsalles(0);
            log.info("Saving new departement {} to the databse ",dep.getName());
            return departementRepository.save(dep);
        } catch (Exception e) {
            throw new IllegalStateException("Error DepartementService in method addDepartement :: " + e.toString());
        }
    }
    @Override
    public Departement updateDepartementById(Departement departement,Long id) {
        try {
            Departement updep = departementRepository.findDepartementById(id);
            updep.setName(departement.getName());
            updep.setUpdatedate(new Timestamp(new Date().getTime()));
            updep.setId(id);
            updep.setNbsalles(departement.getNbsalles());
            log.info("updating departement {} to the database ",departement.getName());

            return departementRepository.save(updep);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error DepartementService in method updateDepartementById :: " + e.toString());
        }
    }

    @Override
    public void deleteDepartementById(Long id) {
        try {
            log.info("Deleting department with id {}  ",id);
            departementRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error DemandeService in method deleteDepartementById :: " + e.toString());
        }
    }
}

