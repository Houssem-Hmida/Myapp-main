package com.example.myapp.presentation.config;

import com.example.myapp.persistence.model.Departement;
import com.example.myapp.persistence.repository.DepartementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DepartementConfig {

    @Bean
    CommandLineRunner commandLineRunner8(
            DepartementRepository departementRepository){
        return args -> {

            Departement departement1 = new Departement("Developpement", 0);
            Departement departement2 = new Departement("Test", 0);
            Departement departement3 = new Departement("Maintenance", 0);


            departementRepository.saveAll(
                    List.of(departement1,departement2,departement3)
            );
        };
    }
}