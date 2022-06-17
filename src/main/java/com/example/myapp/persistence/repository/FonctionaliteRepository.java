package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.Fonctionalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FonctionaliteRepository extends JpaRepository<Fonctionalite,Long> {
    @Query("select f from Fonctionalite f where f.id=:id")
    public Fonctionalite findFonctionaliteById(@Param("id") Long id);

    @Query("select f from Fonctionalite f where f.name=:name")
    public Fonctionalite findFonctionaliteByName(@Param("name") String name);
}
