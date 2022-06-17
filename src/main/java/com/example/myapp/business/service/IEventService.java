package com.example.myapp.business.service;

import com.example.myapp.persistence.model.Event;

import java.sql.Date;
import java.util.List;

public interface IEventService {

   public List<Event> getEvents();

   Event getEventById(Long id);

    Event getEventByTitle(String title);

    public Event addEvent(Event event);

   Event updateEventById(Event event, Long id);

   public void deleteEventById(Long id);
   
   public Integer getStatics (String event_start,String event_end,Long id_salle,Long dep_id,String event_title);
}
