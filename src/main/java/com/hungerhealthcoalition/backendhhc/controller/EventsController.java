package com.hungerhealthcoalition.backendhhc.controller;

import com.hungerhealthcoalition.backendhhc.model.Events;
import com.hungerhealthcoalition.backendhhc.repository.EventsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Events")
@Tag(name = "Event Table", description = "Endpoints for managing Event information")

public class EventsController {
    private EventsRepository eventsRepository;

    public EventsController(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Operation(summary = "Retrieves all Events")
    @GetMapping
    public List<Events> getAllEvents() {
        return eventsRepository.findAll();
    }


    @Operation(summary = "Retrieve event by ID")
    @GetMapping("/{id}")
    public List<Events> getEventsbyID(@PathVariable("id") String id) {
        Optional<Events> eventsOptional = eventsRepository.findById(id);
        List<Events> result = new ArrayList<>();
        eventsOptional.ifPresent(result::add);
        return result;
    }

    @Operation(summary = "Adds a new Event")
    @PostMapping
    public Events addEvents(@RequestBody Events events) {
        eventsRepository.save(events);
        return events;
    }


    @Operation(summary = "Updates a Event by id")
    @PutMapping("/{id}")
    public List<Events> updateEvents(@PathVariable("id") String id, @RequestBody Events events) {
        Optional<Events> existingEventsOptional = eventsRepository.findById(id);
        List<Events> result = new ArrayList<>();

        if (existingEventsOptional.isPresent()) {
            Events existingEvents = existingEventsOptional.get();
            existingEvents.setEventDate(events.getEventDate());
            existingEvents.setEventAddress(events.getEventAddress());
            existingEvents.setEventLimit(events.getEventLimit());
            existingEvents.setEventDescription(events.getEventDescription());
            existingEvents.setEventName(events.getEventName());
            existingEvents.setEventPicture(events.getEventPicture());
            existingEvents.setEventAdditionalPersonLimit(events.getEventAdditionalPersonLimit());
            existingEvents.setGuestAvailability(events.getGuestAvailability());
            existingEvents.setEventAgeLimit(events.getEventAgeLimit());
            eventsRepository.save(existingEvents);
            result.add(existingEvents);
        }

        return result;
    }

    @Operation(summary = "Deletes Event by id")
    @DeleteMapping("/{id}")
    public List<Events> deleteEvents(@PathVariable("id") String id) {
        List<Events> result = new ArrayList<>();
        Optional<Events> eventsOptional = eventsRepository.findById(id);
        if (eventsOptional.isPresent()) {
            eventsRepository.deleteById(id);
            result.add(eventsOptional.get());
        }
        return result;
    }

}
