package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartementRepository  extends JpaRepository<Departement,Long> {
    @Query("select d from Departement d where d.id=:id ")
    public Departement findDepartementById(@Param("id") Long id);

    @Query("select d from Departement d where d.name=:name ")
    public Departement findDepartementByName(@Param("name") String name);

    public List<Departement> findAllByOrderByIdAsc();


}
