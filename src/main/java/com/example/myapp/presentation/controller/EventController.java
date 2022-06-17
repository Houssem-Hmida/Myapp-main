package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.IEventService;
import com.example.myapp.business.service.ILogDataService;
import com.example.myapp.business.service.IUtilisateurService;
import com.example.myapp.business.service.impl.EventService;
import com.example.myapp.persistence.model.Event;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping(path = "/events")
@AllArgsConstructor
public class EventController {

    private final IEventService iEventService;
    private final ILogDataService iLogDataService;
    private final IUtilisateurService iUtilisateurService;



    @PreAuthorize("hasAnyRole('ROLE_RH','ROLE_USER','ROLE_MANAGER')")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getListEvents() {
        try {
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Consulter la liste des évènements");
            return iEventService.getEvents();
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method getListEvents :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long id) {
        try {
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Consulter l'évènement numéro : "+id);
            Event event = iEventService.getEventById(id);
            return new ResponseEntity<>(event, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method getEventById :: " + e.toString());
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @GetMapping(value = "/find/title/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> getEventByTitle(@PathVariable("title") String title) {
        try {
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Consulter l'évènement : "+title);
            Event event = iEventService.getEventByTitle(title);
            return new ResponseEntity<>(event, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method getEventByTitle :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Event addEvent(@RequestBody Event event) {
        try {
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Ajouter un nouveau évènement ");
            return iEventService.addEvent(event);
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method addEvent :: " + e.toString());
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> updateEventById(@RequestBody Event event, @PathVariable("id") Long id) {
        try {
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Mettre à jour l'évènement numéro : " +event.getId());
            Event updateEvent = iEventService.updateEventById(event,id);
            return new ResponseEntity<>(updateEvent, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method updateEventById :: " + e.toString());
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER')")
    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteEventById(@PathVariable("id") Long id) {
        try {
            iLogDataService.saveLogData(iUtilisateurService.currentUserName(),"Supprimer l'évènement numéro  : "+id);
            iEventService.deleteEventById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method deleteEventById :: " + e.toString());
        }
    }
    
    
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_RH','ROLE_MANAGER','ROLE_ADMIN')")
    @PostMapping(value = "/stat", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer getStat(@RequestBody Stat stat) {
        try {
        	//log.info("getStat  ",iEventService.getStatics(stat.getEvent_start(), stat.getEvent_end(), stat.getId_salle(), stat.getDep_id(), stat.getEvent_title()));
            return iEventService.getStatics(stat.getEvent_start(), stat.getEvent_end(), stat.getId_salle(), stat.getDep_id(), stat.getEvent_title());
        } catch (Exception e) {
            throw new IllegalStateException("Error EventController in method addEvent :: " + e.toString());
        }
    }
    
    
}
