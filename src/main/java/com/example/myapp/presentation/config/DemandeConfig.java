//package com.example.myapp.presentation.config;
//
//import com.example.myapp.persistence.model.Demande;
//import com.example.myapp.persistence.repository.DemandeRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Configuration
//public class DemandeConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner19(
//            DemandeRepository repository2){
//        return args -> {
//            Demande jourdepres = new Demande(
//                    "dem1",
//                    "family business",
//                    LocalDate.of(2022,4,2),
//                    LocalDate.of(2022,4,3)
//            );
//            Demande conger = new Demande(
//                    "dem2",
//                    "maladee",
//                    LocalDate.of(2022,7,21),
//                    LocalDate.of(2022,7,29)
//            );
//            repository2.saveAll(
//                    List.of(jourdepres,conger)
//            );
//        };
//    }
//}
