package com.hungerhealthcoalition.backendhhc.controller;


import com.hungerhealthcoalition.backendhhc.model.Registration;
import com.hungerhealthcoalition.backendhhc.repository.RegistrationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Registration")
@Tag(name = "Goal Table", description = "Endpoints for managing Client's Goals information")
public class RegistrationController {


    private RegistrationRepository registrationRepository;

    public RegistrationController(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Operation(summary = "Retrieves all Registrations")
    @GetMapping
    public List<Registration> getAllPairings() {
        return registrationRepository.findAll();
    }

    @Operation(summary = "Retrieves all Registrations by Client ID")
    @GetMapping("/{id}")
    public List<Registration> getPairingByID(@PathVariable("id") int id) {
        List<Registration> result = registrationRepository.findRegistrationByClientInfoClientID(id);
        return result;
    }

    @Operation(summary = "Retrieves all Registrations by Event ID")
    @GetMapping("/eventID/{eventID}")
    public List<Registration> getParingByEventID(@PathVariable("eventID") int eventID) {
        return registrationRepository.findRegistrationByEventsEventId(eventID);
    }


    @Operation(summary = "Retrieves count of amount of people going to Event by Event ID")
    @GetMapping("/count/{eventID}")
    public int countByEventID(@PathVariable("eventID") int eventID) {
        List<Registration> registrations = registrationRepository.findRegistrationByEventsEventId(eventID);

        int registrationCount = registrations.size();
        int totalGuestCount = registrations.stream().mapToInt(Registration::getGuestCount).sum();

        return totalGuestCount + registrationCount;
    }


    @Operation(summary = "Adds Registrations by Client ID and Event ID")
    @PostMapping
    public Registration addPairing(@RequestBody Registration registration) {


        // Check for duplicate registration based on clientInfo and eventID
        Optional<Registration> existingRegistration = registrationRepository.findRegistrationByClientInfoClientIDAndEventsEventId(
                registration.getClientInfo().getClientID(), registration.getEvents().getEventId());

        if (existingRegistration.isPresent()) {
            // Throw an exception for clarity
            throw new RuntimeException("Registration with client ID " + registration.getClientInfo().getClientID() +
                    " and eventID " + registration.getEvents().getEventId() + " already exists");
        }

        // Persist the new registration
        return registrationRepository.save(registration);
    }

    @Operation(summary = "Updates all Registrations by Client ID and Event ID")
    @PutMapping("/{id}/{eventID}")
    public List<Registration> updateRegistration(@PathVariable("id") int clientId, @PathVariable("eventID") int eventID, @RequestBody Registration registration) {
        // Validate client existence first
        if (!registrationRepository.existsByClientInfoClientID(clientId)) {
            throw new RuntimeException("Client with ID " + clientId + " not found");
        }

        Optional<Registration> existingRegistrationOptional = registrationRepository.findRegistrationByClientInfoClientIDAndEventsEventId(clientId, eventID);
        List<Registration> result = new ArrayList<>();

        if (existingRegistrationOptional.isPresent()) {
            Registration existingRegistration = existingRegistrationOptional.get();

            existingRegistration.setEvents(registration.getEvents());
            existingRegistration.setGuestCount(registration.getGuestCount());
            registrationRepository.save(existingRegistration);
            result.add(existingRegistration);
        }

        return result;
    }

    @Operation(summary = "Delete Registrations by Client ID and Event ID")
    @DeleteMapping("/{id}/{eventID}")
    @Transactional
    public ResponseEntity<Void> deletePairingByIdAndEventID(@PathVariable("id") int id, @PathVariable("eventID") int eventID) {
        registrationRepository.deleteRegistrationByClientInfoClientIDAndEventsEventId(id, eventID);

        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Delete all Registrations by Event ID")
    @DeleteMapping("/{eventID}")
    @Transactional
    public ResponseEntity<Void> deletePairingByEventID(@PathVariable("eventID") int eventID) {
        registrationRepository.deleteRegistrationByEventsEventId(eventID);

        return ResponseEntity.noContent().build();
    }


}
