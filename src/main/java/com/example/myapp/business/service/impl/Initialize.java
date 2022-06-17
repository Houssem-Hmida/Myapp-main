package com.example.myapp.business.service.impl;

import com.example.myapp.persistence.model.Employee;
import com.example.myapp.persistence.model.Profil;
import com.example.myapp.persistence.model.Utilisateur;
import com.example.myapp.persistence.repository.EmployeeRepository;
import com.example.myapp.persistence.repository.ProfilRepository;
import com.example.myapp.persistence.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class Initialize {

    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProfilRepository profilRepository;



    @Autowired
    private EmployeeRepository employeeRepository;


    public void initRoleAndUser() {
        Profil adminRole = new Profil(
                "ADMIN",
                "Gérer les utilisateurs, gérer les profils, gérer les salles, gérer les departements "
        );
        Profil RhRole = new Profil(
                "RH",
                "bbbb"
        );
        Profil simpleUserRole = new Profil(
                "USER",
                "cccc"
        );
        Profil managerRole = new Profil(
                "MANAGER",
                "dddd"
        );
        profilRepository.saveAll(
                List.of(adminRole, RhRole,simpleUserRole,managerRole)
        );

        Utilisateur RhUser = new Utilisateur(
                "houssem",
                passwordEncoder.encode("0000"),
                // "0000",
                "houssemhmida1212@gmail.com",
                new HashSet<Profil>(),
                new Employee("hmida","houssem","", LocalDate.of(2000, 4, 2),"12 sfaxx","90526478","Non séléctionné","Non séléctionné","Non séléctionné","Homme")
        );
        Utilisateur adminUser = new Utilisateur(
                "yassine",
                passwordEncoder.encode("1111"),
                //  "1111",
                "ymahfoudh55@gmail.com",
                new HashSet<Profil>(),
                new Employee("mahfoudh","yassine","",LocalDate.of(2000, 06, 29),"68 dar chaabene","90222545","Non séléctionné","Non séléctionné","Non séléctionné","Homme")
        );
        Utilisateur simpleUser = new Utilisateur(
                "oussama",
                passwordEncoder.encode("2222"),
                //"2222",
                "oussama@gmail.com",
                new HashSet<Profil>(),
                new Employee("ben hamouda","oussama","",LocalDate.of(1992, 01, 04)," tunis","21548963","Non séléctionné","Non séléctionné","Non séléctionné","Homme")
        );
        Utilisateur managerUser = new Utilisateur(
                "john",
                passwordEncoder.encode("3333"),
                //"3333",
                "john@gmail.com",
                new HashSet<Profil>(),
                new Employee("menguez","john","",LocalDate.of(1985, 10, 11),"nabeul","53264879","Non séléctionné","Non séléctionné","Non séléctionné","Homme")
        );

      /*  Employee Yassine = new Employee(
                "Mahfoudh",
                "Yassine",
                "admin",
                Boolean.TRUE,
                LocalDate.of(2000, 6, 29),
                "68 dar chaabene",
                "902222545",
                "webservice",
                5,
                "Yassine"
        );
        Employee Houssem = new Employee(
                "Hmida",
                "Houssem",
                "RH",
                Boolean.FALSE,
                LocalDate.of(2000, 4, 2),
                "14 sfax",
                "99823490",
                "websocket",
                7,
                "Yassine"
        );
        Employee Oussama = new Employee(
                "Mahfoudh",
                "Oussama",
                "user",
                Boolean.TRUE,
                LocalDate.of(1995, 6, 29),
                "68 dar chaabene",
                "90024425",
                "webservice",
                5,
                "Yassine"
        );
        Employee John = new Employee(
                "Mengueza",
                "John",
                "manager",
                Boolean.TRUE,
                LocalDate.of(1999, 6, 29),
                "15 salzburg",
                "92458967",
                "android",
                5,
                "Yassine"
        );

        employeeRepository.saveAll(
                List.of(Yassine, Houssem, Oussama,John)
        );*/


        Set<Profil> simpleUserRoles = new HashSet<>();
        simpleUserRoles.add(simpleUserRole);
        simpleUser.setProfils(simpleUserRoles);


        Set<Profil> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setProfils(adminRoles);

        Set<Profil> RhRoles = new HashSet<>();
        RhRoles.add(RhRole);
        RhUser.setProfils(RhRoles);


        Set<Profil> managerRoles = new HashSet<>();
        managerRoles.add(managerRole);
        managerRoles.add(simpleUserRole);
        managerUser.setProfils(managerRoles);

        utilisateurRepository.saveAll(
                List.of(adminUser,RhUser,simpleUser,managerUser)
        );
    }
}