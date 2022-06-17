package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.IFonctionaliteService;
import com.example.myapp.persistence.model.Fonctionalite;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/fonctionalite")
@AllArgsConstructor
public class FonctionaliteController {
    private final IFonctionaliteService iFonctionaliteService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Fonctionalite> getListFonctionalite() {
        try {
            return iFonctionaliteService.getListFonctionalite();
        } catch (Exception e) {
            throw new IllegalStateException("Error FonctionaliteController in method getListDemande :: " + e.toString());
        }
    }

//    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Fonctionalite> getFonctionaliteById(@PathVariable("id") Long id) {
//        try {
//            Fonctionalite fonctionalite = iFonctionaliteService.getFonctionaliteById(id);
//            return new ResponseEntity<>(fonctionalite, HttpStatus.OK);
//        } catch (Exception e) {
//            throw new IllegalStateException("Error FonctionaliteController in method getDemandeById :: " + e.toString());
//        }
//    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Fonctionalite addFonctionalite(@RequestBody Fonctionalite fonctionalite) {
        try {
            return iFonctionaliteService.addFonctionalite(fonctionalite);
        } catch (Exception e) {
            throw new IllegalStateException("Error FonctionaliteController in method addDemande :: " + e.toString());
        }
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Fonctionalite> updateFonctionalite(@RequestBody Fonctionalite fonctionalite, @PathVariable("id") Long id) {
        try {
            Fonctionalite updateFonctionalite = iFonctionaliteService.updateFonctionalite(fonctionalite, id);
            return new ResponseEntity<>(updateFonctionalite, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error FonctionaliteController in method updateFonctionalite :: " + e.toString());
        }
    }

    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteFonctionalite(@PathVariable("id") Long id) {
        try {
            iFonctionaliteService.deleteFonctionalite(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error FonctionaliteController in method deleteFonctionalite :: " + e.toString());
        }
    }

}