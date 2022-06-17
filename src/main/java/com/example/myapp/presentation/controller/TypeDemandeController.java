package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.ITypeDemandeService;
import com.example.myapp.persistence.model.Demande;
import com.example.myapp.persistence.model.EtatDemande;
import com.example.myapp.persistence.model.TypeDemande;
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
@RequestMapping(path = "/typedemande")
@AllArgsConstructor
public class TypeDemandeController {
    public final ITypeDemandeService iTypeDemandeService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TypeDemande> getListTypeDemande() {
        try {
            return iTypeDemandeService.getListTypeDemande();
        } catch (Exception e){
            throw new IllegalStateException("Error TypeDemandeController in method getListTypeDemande :: " + e.toString());
        }
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeDemande> getTypeDemandeById (@PathVariable("id") Long id) {
        try {
            TypeDemande typeDemande = iTypeDemandeService.getTypeDemandeById(id);
            return new ResponseEntity<>(typeDemande, HttpStatus.OK);
        } catch (Exception e){
            throw new IllegalStateException("Error TypeDemandeController in method getTypeDemandeById :: " + e.toString());
        }
    }
}
