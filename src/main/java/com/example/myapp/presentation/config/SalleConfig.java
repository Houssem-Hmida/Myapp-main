package com.example.myapp.presentation.config;

import com.example.myapp.persistence.model.Salle;
import com.example.myapp.persistence.repository.SalleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class SalleConfig {

    @Bean
    CommandLineRunner commandLineRunner122(
            SalleRepository repository22){
        return args -> {
            Salle salle1 = new Salle(
                    "reunion",
                    "A1",
                    15,
                    30,
                    "Developpement"

            );
            Salle salle2 = new Salle(
                    "travail",
                    "B3",
                    8,
                    50,
                    "Test"
            );
            repository22.saveAll(
                    List.of(salle1,salle2)
            );
        };
    }
}
