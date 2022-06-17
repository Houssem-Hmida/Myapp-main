//package com.example.myapp.presentation.config;
//
//import com.example.myapp.persistence.model.Fonctionalite;
//import com.example.myapp.persistence.repository.FonctionaliteRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//@Configuration
//public class FonctionaliteConfig {
//    @Bean
//    CommandLineRunner commandLineRunner44(
//            FonctionaliteRepository repository){
//        return args -> {
//            Fonctionalite f1 = new Fonctionalite (
//                    "gerer profil",
//                    "admin"
//            );
//            Fonctionalite f2 = new Fonctionalite (
//                    "consulter salle",
//                    "employee"
//            );
//
//            repository.saveAll(
//                    List.of(f1,f2)
//            );
//        };
//    }
//}
