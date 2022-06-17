package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IProfilService;
import com.example.myapp.persistence.model.Profil;
import com.example.myapp.persistence.repository.ProfilRepository;
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
public class ProfilService implements IProfilService {
    public final ProfilRepository profilRepository;
    @Override

    public List<Profil> getListProfil() {
        try {
            log.info("Fetching all profils ");
            return profilRepository.findAllByOrderByIdAsc();
        } catch (Exception e){
            throw new IllegalStateException("Error ProfilService in method getListProfil :: " + e.toString());

        }
    }

    @Override
    public Profil getProfilById(Long id){
        try {
            if (id == null)
                return new Profil();
            Profil d = profilRepository.findProfilById(id);
            if (d == null)
                return new Profil();
            log.info("Fetching profil with id :{} ",id);

            return d;
        } catch (Exception e){
            throw new IllegalStateException("Error ProfilService in method getDemandeById :: " + e.toString());
        }

    }


    @Override
    public Profil addProfil(Profil profil) {
        try {
            Profil objNomUnique = profilRepository.findProfilByName(profil.getName());

            if ( objNomUnique != null)
                throw new IllegalStateException("Profil name token");

            profil.setCreationdate(new Timestamp(new Date().getTime()));

            log.info("Saving new profil {} to the databse ",profil.getName());
            return profilRepository.save(profil);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProfilService in method addProfil :: " + e.toString());
        }
    }

    @Override
    public Profil updateProfil(Profil profil,Long id) {
        try {
            Profil upprof = profilRepository.findProfilById(profil.getId());
            upprof.setUpdatedate(new Timestamp(new Date().getTime()));
            upprof.setDescription(profil.getDescription());
            upprof.setId(id);
            log.info("updating profil {} to the database ",profil.getName());
            return profilRepository.save(upprof);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error ProfilService in method updateProfil :: " + e.toString());
        }
    }

    @Override
    public void deleteProfil(Long id) {
        try {
            log.info("Deleting profil with id {}  ",id);
            profilRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error ProfilService in method deleteProfil :: " + e.toString());
        }
    }
}
