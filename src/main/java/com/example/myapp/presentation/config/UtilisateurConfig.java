//package com.example.myapp.presentation.config;
//
//
//import com.example.myapp.persistence.model.Profil;
//import com.example.myapp.persistence.model.Utilisateur;
//import com.example.myapp.persistence.repository.ProfilRepository;
//import com.example.myapp.persistence.repository.UtilisateurRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Configuration
//public class UtilisateurConfig {
//
//    @Autowired
//  private PasswordEncoder passwordEncoder;
//    @Bean
//    CommandLineRunner commandLineRunner16(
//            UtilisateurRepository repository, ProfilRepository profilRepository){
//        return args -> {
//            Profil adminRole = new Profil(
//                    "Admin"
//            );
//            Profil RhRole = new Profil(
//                    "RH"
//            );
//            profilRepository.saveAll(
//                    List.of(adminRole, RhRole)
//            );
//
//            Utilisateur RhUser = new Utilisateur(
//                    "houssem",
//                    passwordEncoder.encode("00000000"),
//                    "houssem@gmail.com",
//                    new HashSet<Profil>()
//            );
//            Utilisateur adminUser = new Utilisateur(
//                    "yasssine",
//                    passwordEncoder.encode("11111111"),
//                    "yassine@gmail.com",
//                    new HashSet<Profil>()
//            );
//            Set<Profil> adminRoles = new HashSet<>();
//            adminRoles.add(adminRole);
//            adminUser.setProfils(adminRoles);
//
//            Set<Profil> RhRoles = new HashSet<>();
//            RhRoles.add(RhRole);
//            RhUser.setProfils(RhRoles);
//
//            repository.saveAll(
//                    List.of(adminUser,RhUser)
//            );
//        };
//    }
//}