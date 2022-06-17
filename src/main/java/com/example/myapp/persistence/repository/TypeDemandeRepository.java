package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.TypeDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDemandeRepository  extends JpaRepository<TypeDemande,Long> {
    @Query("select t from TypeDemande t where t.id=:id ")
    public TypeDemande findTypeDemandeById(@Param("id") Long id);
}
