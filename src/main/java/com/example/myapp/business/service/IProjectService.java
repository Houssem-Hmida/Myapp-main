package com.example.myapp.business.service;

import com.example.myapp.persistence.model.Project;

import java.util.List;

public interface IProjectService {
    public List<Project> getListProjet();
    public Project addProjet(Project projet);
    public void deleteProjet(Long id);
    public Project updateProjet(Project projet, Long id);
    public Project getProjetById(Long id);
}
