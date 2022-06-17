package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalleRepository extends JpaRepository<Salle,Long> {
    @Query("select s from Salle s  where s.id=:id ")
    public Salle findSalleById(@Param("id") Long id);

    @Query("select s from Salle s  where s.nom=:nom ")
    public Salle findSalleByNom(@Param("nom") String nom);

    public List<Salle> findAllByOrderByIdAsc();

}
