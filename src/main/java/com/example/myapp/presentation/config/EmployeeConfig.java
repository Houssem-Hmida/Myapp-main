//package com.example.myapp.presentation.config;
//
//import com.example.myapp.persistence.model.Employee;
//import com.example.myapp.persistence.repository.EmployeeRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Configuration
//public class EmployeeConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(
//            EmployeeRepository employeeRepository) {
//        return args -> {
//
//            Employee Yassine = new Employee(
//                    "Yassine",
//                    "Mahfoudh",
//                    "admin",
//                    Boolean.TRUE,
//                    LocalDate.of(2000, 6, 29),
//                    "68 dar chaabene"
//            );
//            Employee Houssem = new Employee(
//                    "Houssem",
//                    "Hmida",
//                    "RH",
//                    Boolean.FALSE,
//                    LocalDate.of(2000, 4, 2),
//                    "14 sfax"
//            );
//            employeeRepository.saveAll(
//                    List.of(Yassine, Houssem)
//            );
//        };
//    }
//}