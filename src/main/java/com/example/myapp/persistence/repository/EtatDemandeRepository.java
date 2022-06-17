package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.EtatDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatDemandeRepository  extends JpaRepository<EtatDemande,Long> {
    @Query("select e from EtatDemande e where e.id=:id ")
    public EtatDemande findEtatDemandeById(@Param("id") Long id);
}
