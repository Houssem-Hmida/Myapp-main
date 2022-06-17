//package com.example.myapp.presentation.config;
//
//import com.example.myapp.persistence.model.Event;
//import com.example.myapp.persistence.repository.EventRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//
//@Configuration
//public class EventConfig {
//    @Bean
//    CommandLineRunner eventconfig(
//            EventRepository eventRepository){
//        return args -> {
//
//
//            Event event1 = new Event("Presentielle", "2022-05-15", "2022-05-18", "yassine","DTSTART:20220503\nRRULE:FREQ=WEEKLY;UNTIL=20220527;BYDAY=MO,FR");
//            //Event event2 = new Event("A distance", "2022-05-22", "2022-05-25", "oussama");
//            //Event event3 = new Event("Congé", "2022-05-24", "2022-05-28", "john");
//            eventRepository.saveAll(
//                  // List.of(event1,event2,event3)
//                     List.of(event1)
//
//
//            );
//        };
//    }
//}
