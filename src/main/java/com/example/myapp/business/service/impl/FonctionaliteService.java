package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IDemandeService;
import com.example.myapp.business.service.IFonctionaliteService;
import com.example.myapp.persistence.model.Demande;
import com.example.myapp.persistence.model.Fonctionalite;
import com.example.myapp.persistence.repository.DemandeRepository;
import com.example.myapp.persistence.repository.FonctionaliteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class FonctionaliteService implements IFonctionaliteService {
    public final FonctionaliteRepository fonctionaliteRepository;

    @Override
    public List<Fonctionalite> getListFonctionalite() {
        try {
            return fonctionaliteRepository.findAll();
        } catch (Exception e){
            throw new IllegalStateException("Error FonctionaliteService in method getListFonctionalite :: " + e.toString());

        }
    }

//    @Override
//    public Fonctionalite getFonctionaliteById(Long id){
//        try {
//            if (id == null)
//                return new Fonctionalite();
//            Fonctionalite d = fonctionaliteRepository.findFonctionaliteById(id);
//            if (d == null)
//                return new Fonctionalite();
//            return d;
//        } catch (Exception e){
//            throw new IllegalStateException("Error FonctionaliteService in method getDemandeById :: " + e.toString());
//        }
//
//    }


    @Override
    public Fonctionalite addFonctionalite(Fonctionalite fonctionalite) {
        try {
            Fonctionalite objNomUnique = fonctionaliteRepository.findFonctionaliteByName(fonctionalite.getName());

            if ( objNomUnique != null)
                throw new IllegalStateException("Fonctionalite name token");

            return fonctionaliteRepository.save(fonctionalite);
        } catch (Exception e) {
            throw new IllegalStateException("Error FonctionaliteService in method addFonctionalite :: " + e.toString());
        }
    }
    @Transactional
    @Override
    public Fonctionalite updateFonctionalite(Fonctionalite fonctionalite,Long id) {
        try {
            Fonctionalite upfonc = fonctionaliteRepository.findFonctionaliteById(id);
            upfonc.setName(fonctionalite.getName());
            upfonc.setDesignation(fonctionalite.getDesignation());
            upfonc.setUpdatedate(new Timestamp(new Date().getTime()));
            upfonc.setId(id);

            return fonctionaliteRepository.save(upfonc);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error FonctionaliteService in method updateFonctionalite :: " + e.toString());
        }
    }

    @Transactional
    @Override
    public void deleteFonctionalite(Long id) {
        try {
            fonctionaliteRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error FonctionaliteService in method deleteFonctionalite :: " + e.toString());
        }
    }



}