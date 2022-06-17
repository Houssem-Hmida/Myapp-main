package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IProjectService;
import com.example.myapp.persistence.model.Project;
import com.example.myapp.persistence.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class ProjectService implements IProjectService {
    public final ProjectRepository projectRepository;

    @Override
    public List<Project> getListProjet() {
        try {
            log.info("Fetching all projects ");
            return projectRepository.findAllByOrderByIdAsc();
        } catch (Exception e){
            throw new IllegalStateException("Error ProjetService in method getListProjet :: " + e.toString());

        }
    }

    @Override
    public Project getProjetById(Long id){
        try {
            if (id == null)
                return new Project();
            Project d = projectRepository.findProjectById(id);
            if (d == null)
                return new Project();
            log.info("Fetching project with id :{} ",id);

            return d;
        } catch (Exception e){
            throw new IllegalStateException("Error ProjetService in method getProjetById :: " + e.toString());
        }

    }


    @Override
    public Project addProjet(Project projet) {
        try {
            Project objNomUnique = projectRepository.findProjectByName(projet.getName());

            if ( objNomUnique != null)
                throw new IllegalStateException("Projet name token");

            projet.setCreationdate(new Timestamp(new Date().getTime()));
            log.info("Saving new project {} to the databse ",projet.getName());
            return projectRepository.save(projet);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProjetService in method addProjet :: " + e.toString());
        }
    }

    @Override
    public Project updateProjet(Project project, Long id) {
        try {
            Project upproj = projectRepository.findProjectById(id);
            upproj.setName(project.getName());
            upproj.setStartdate(project.getStartdate());
            upproj.setEnddate(project.getEnddate());
            upproj.setDescription(project.getDescription());
            upproj.setPriority(project.getPriority());
            upproj.setUpdatedate(new Timestamp(new Date().getTime()));
            upproj.setId(id);
            log.info("updating project {} to the database ",project.getName());
            return projectRepository.save(upproj);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error ProjetService in method updateProjet :: " + e.toString());
        }
    }
    @Override
    public void deleteProjet(Long id) {
        try {
            log.info("Deleting project with id {}  ",id);
            projectRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error ProjetService in method deleteProjet :: " + e.toString());
        }
    }
}


