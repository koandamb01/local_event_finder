package com.local.events.app.services;

import com.local.events.app.models.Event;
import com.local.events.app.models.User;
import com.local.events.app.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepo;

    public EventService(EventRepository eventRepo) {
        this.eventRepo = eventRepo;
    }


    // create an new event
    public Event createEvent(Event event, User user){
        event.setHost(user);
        return this.eventRepo.save(event);
    }


    // find all events
    public List<Event> findAllEvents(){ return this.eventRepo.findAll();}

    // find an event by Id
    public Event findEventById(Long id){
        Optional<Event> res = this.eventRepo.findById(id);
        if(res.isPresent()){
            return res.get();
        }
        else{
            return null;
        }
    }
}
