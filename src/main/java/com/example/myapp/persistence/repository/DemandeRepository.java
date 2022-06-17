package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DemandeRepository
        extends JpaRepository<Demande,Long> {

    @Query("select d from Demande d where d.id=:id ")
    public Demande findDemandeById(@Param("id") Long id);

    @Query("select d from Demande d where d.title=:title ")
    public Demande findDemandeBytitle(@Param("title") String title);

    public List<Demande> findAllByOrderByIdAsc();


}
