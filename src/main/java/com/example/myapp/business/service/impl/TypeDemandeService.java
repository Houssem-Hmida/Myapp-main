package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.ITypeDemandeService;
import com.example.myapp.persistence.model.TypeDemande;
import com.example.myapp.persistence.repository.TypeDemandeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TypeDemandeService implements ITypeDemandeService {
    public final TypeDemandeRepository typeDemandeRepository;

    @Override
    public List<TypeDemande> getListTypeDemande() {
        try {
            return typeDemandeRepository.findAll();
        } catch (Exception e){
            throw new IllegalStateException("Error TypeDemandeService in method getListTypeDemande :: " + e.toString());

        }
    }

    @Override
    public TypeDemande getTypeDemandeById(Long id){
        try {
            if (id == null)
                return new TypeDemande();
            TypeDemande t = typeDemandeRepository.findTypeDemandeById(id);
            if (t == null)
                return new TypeDemande();
            return t;
        } catch (Exception e){
            throw new IllegalStateException("Error getTypeDemandeById in method getTypeDemandeById :: " + e.toString());
        }
    }
}
