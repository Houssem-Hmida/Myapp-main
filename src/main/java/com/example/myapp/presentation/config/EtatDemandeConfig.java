package com.example.myapp.presentation.config;

import com.example.myapp.persistence.model.EtatDemande;
import com.example.myapp.persistence.repository.EtatDemandeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EtatDemandeConfig {

    @Bean
    CommandLineRunner commandLineRunner61(
            EtatDemandeRepository repository3){
        return args -> {
            EtatDemande etat1 = new EtatDemande(
                    "en attente"

            );
            EtatDemande etat2 = new EtatDemande(
                    "refusé"
            );
            EtatDemande etat3 = new EtatDemande(
                    "accepté"
            );
            repository3.saveAll(
                    List.of(etat1,etat2)
            );
        };
    }

}