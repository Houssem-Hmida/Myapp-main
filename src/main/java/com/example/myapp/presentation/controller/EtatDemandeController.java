package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.IEtatDemandeService;
import com.example.myapp.persistence.model.Demande;
import com.example.myapp.persistence.model.EtatDemande;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/etatdemande")
@AllArgsConstructor
public class EtatDemandeController {
    public final IEtatDemandeService iEtatDemandeService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EtatDemande> getListEtatDemande() {
        try {
            return iEtatDemandeService.getListEtatDemande();
        } catch (Exception e){
            throw new IllegalStateException("Error EtatDemandeController in method getListEtatDemande :: " + e.toString());
        }
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EtatDemande> getEtatDemandeById (@PathVariable("id") Long id) {
        try {
            EtatDemande etatDemande = iEtatDemandeService.getEtatDemandeById(id);
            return new ResponseEntity<>(etatDemande, HttpStatus.OK);
        } catch (Exception e){
            throw new IllegalStateException("Error EtatDemandeController in method getEtatDemandeById :: " + e.toString());
        }
    }
}
