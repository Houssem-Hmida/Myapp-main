package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    @Query("select d from Project d where d.id=:id ")
    public Project findProjectById(@Param("id") Long id);

    @Query("select d from Project d where d.name=:name ")
    public Project findProjectByName(@Param("name") String name);

    public List<Project> findAllByOrderByIdAsc();

}
