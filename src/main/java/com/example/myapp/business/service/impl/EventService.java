package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IEventService;
import com.example.myapp.persistence.model.Event;
import com.example.myapp.persistence.repository.EventRepository;
import com.example.myapp.persistence.repository.eventDao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Transactional
@AllArgsConstructor
@Slf4j
@Service
public class EventService implements IEventService {

    @Autowired
    EventRepository eventRepository;
    
    @Autowired
    eventDao eventDao;

    @Override
    public List<Event> getEvents(){
        try{
            log.info("Fetching all events ");
            return eventRepository.findAllByOrderByIdAsc();
        } catch (Exception e){
            throw new IllegalStateException("Error EventService in method getEvents " + e.toString());
        }
    }

    @Override
    public Event getEventById(Long id){
        try {
            if (id == null)
                return new Event();
            Event e = eventRepository.findEventById(id);
            if (e == null)
                return new Event();
            log.info("Fetching event with id :{} ",id);
            return e;
        } catch (Exception e){
            throw new IllegalStateException("Error EventService in method getEventById :: " + e.toString());
        }

    }

    @Override
    public Event getEventByTitle(String title){
        try {
            if (title == null)
                return new Event();
            Event e = eventRepository.findEventByTitle(title);
            if (e == null)
                return new Event();
            log.info("Fetching event with title :{} ",title);
            return e;
        } catch (Exception e){
            throw new IllegalStateException("Error EventService in method getEventByTitle :: " + e.toString());
        }

    }

    @Override
    public Event addEvent(Event event) {
        try {
            /*Event objNomUnique = eventRepository.findEventByTitle(event.getTitle());

            if ( objNomUnique != null)
                throw new IllegalStateException("Event title token");*/

            log.info("Saving new event {} to the databse ",event.getTitle());

            return eventRepository.save(event);
        } catch (Exception e) {
            throw new IllegalStateException("Error EventService in method addEvent :: " + e.toString());
        }
    }

    @Override
    public Event updateEventById(Event event,Long id) {
        try {
            Event upevent = eventRepository.findEventById(id);
            upevent.setTitle(event.getTitle());
            upevent.setDescription(event.getDescription());
            upevent.setEmployee(event.getEmployee());
            upevent.setStart(event.getStart());
            upevent.setEnd(event.getEnd());
            upevent.setColor(event.getColor());
            upevent.setRrule(event.getRrule());
            upevent.setFrequency(event.getFrequency());
//            upevent.setEveryNday(event.getEveryNday());
//            upevent.setEveryNmonth(event.getEveryNmonth());
//            upevent.setWeekday(event.getWeekday());
//            upevent.setMonthday(event.getMonthday());
            upevent.setId(id);
            upevent.setEmployeeid(event.getEmployeeid());
            log.info("updating  event {} to the databse ",event.getTitle());

            return eventRepository.save(upevent);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error EventService in method updateEventById :: " + e.toString());
        }
    }



    @Override
    public void deleteEventById(Long id) {
        try {
            log.info("Deleting Event with id {}  ",id);
            eventRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error EventService in method deleteEventById :: " + e.toString());
        }
    }

	@Override
	public Integer getStatics(String event_start, String event_end, Long id_salle, Long dep_id, String event_title) {
		try {
        

            return eventDao.getStatics(event_start, event_end, id_salle, dep_id, event_title);
        } catch (Exception e) {
            throw new IllegalStateException("Error EventService in method getStatics :: " + e.toString());
        }
	}

}

