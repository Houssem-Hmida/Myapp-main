package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.ILogDataService;
import com.example.myapp.business.service.ISalleService;
import com.example.myapp.business.service.IUtilisateurService;
import com.example.myapp.persistence.model.Salle;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/salle")
@AllArgsConstructor
public class SalleController {
    private final ISalleService iSalleService;
    private final ILogDataService iLogDataService;
    private final IUtilisateurService iUtilisateurService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Salle> getListSalle() {
        try {
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Consulter la liste des salles ");
            return iSalleService.getListSalle();
        } catch (Exception e) {
            throw new IllegalStateException("Error SalleController in method getListSalle :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Salle> getSalleById(@PathVariable("id") Long id) {
        try {
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Consulter la salle numéro : "+id);
            Salle salle = iSalleService.getSalleById(id);
            return new ResponseEntity<>(salle, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error SalleController in method getSalleById :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_RH')")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Salle addSalle(@RequestBody Salle salle) {
        try {
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Ajouter une salle salle");
            return iSalleService.addSalle(salle);
        } catch (Exception e) {
            throw new IllegalStateException("Error SalleController in method addSalle :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_RH')")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Salle> updateSalle(@RequestBody Salle salle, @PathVariable("id") Long id) {
        try {
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Mettre à jour la salle : " +salle.getNom());
            Salle updateS = iSalleService.updateSalle(salle,id);
            return new ResponseEntity<>(updateS, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error SalleController in method updateSalle :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_RH')")
    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteSalle(@PathVariable("id") Long id) {
        try {
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Supprimer la salle numéro  : "+id);
            iSalleService.deleteSalle(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error SalleController in method deleteSalle :: " + e.toString());
        }
    }

}