package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfilRepository extends JpaRepository<Profil,Long> {
    @Query("select p from Profil p where p.id=:id")
    public Profil findProfilById(@Param("id") Long id);

    @Query("select p from Profil p where p.name=:name")
    public Profil findProfilByName(@Param("name") String name);

    public List<Profil> findAllByOrderByIdAsc();


}
