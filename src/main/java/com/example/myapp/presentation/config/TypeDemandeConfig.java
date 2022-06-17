package com.example.myapp.presentation.config;

import com.example.myapp.persistence.model.TypeDemande;
import com.example.myapp.persistence.repository.TypeDemandeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TypeDemandeConfig {

    @Bean
    CommandLineRunner commandLineRunner18(
            TypeDemandeRepository typeDemandeRepository){
        return args -> {

            TypeDemande type1 = new TypeDemande("À distance");
            TypeDemande type2 = new TypeDemande("Congé");
            TypeDemande type3 = new TypeDemande("Présentiel");


            typeDemandeRepository.saveAll(
                    List.of(type1,type2,type3)
            );


        };
    }
}