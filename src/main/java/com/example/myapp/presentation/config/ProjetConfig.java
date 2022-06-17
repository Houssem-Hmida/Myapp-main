package com.example.myapp.presentation.config;

import com.example.myapp.persistence.model.Project;
import com.example.myapp.persistence.repository.ProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
@Configuration
public class ProjetConfig {
    @Bean
   CommandLineRunner commandLineRunner4( ProjectRepository repository){
        return args -> {
            Project projet1 = new Project(
                    "devops",
                    1,
                    "application web",
                    LocalDate.of(2022,3,2),
                    LocalDate.of(2022,7,28)

            );
            Project projet2 = new Project(
                    "mobdesign",
                    2,
                    "application mobile",
                    LocalDate.of(2022,4,21),
                    LocalDate.of(2022,8,20)

            );
            repository.saveAll(
                    List.of(projet1,projet2)
            );
        };
    }
}
