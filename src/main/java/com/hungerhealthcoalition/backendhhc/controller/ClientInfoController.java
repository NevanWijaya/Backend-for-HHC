package com.hungerhealthcoalition.backendhhc.controller;


import com.hungerhealthcoalition.backendhhc.model.ClientInfo;
import com.hungerhealthcoalition.backendhhc.repository.ClientInfoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Info")
@Tag(name = "Client Info", description = "Endpoints for managing client information")
public class ClientInfoController {
    private ClientInfoRepository clientInfoRepository;

    public ClientInfoController(ClientInfoRepository clientInfoRepository) {
        this.clientInfoRepository = clientInfoRepository;
    }

    @GetMapping
    @Operation(summary = "Retrieve all client infos")
    public List<ClientInfo> getAllClientsInfos() {
        return clientInfoRepository.findAll();
    }


    @Operation(summary = "Retrieve client by ID")
    @GetMapping("/{id}")
    public List<ClientInfo> getClientbyID(@PathVariable("id") String id) {
        Optional<ClientInfo> clientInfoOptional = clientInfoRepository.findById(id);
        List<ClientInfo> result = new ArrayList<>();
        clientInfoOptional.ifPresent(result::add);
        return result;
    }

    @Operation(summary = "Add a new client info")
    @PostMapping
    public ClientInfo addClientInfo(@RequestBody ClientInfo clientInfo) {
        clientInfoRepository.save(clientInfo);
        return clientInfo;
    }

    @Operation(summary = "Delete client info by ID")
    @DeleteMapping("/{id}")
    public List<ClientInfo> deleteClientInfo(@PathVariable("id") String id) {
        List<ClientInfo> result = new ArrayList<>();
        Optional<ClientInfo> clientOptional = clientInfoRepository.findById(id);
        if (clientOptional.isPresent()) {
            clientInfoRepository.deleteById(id);
            result.add(clientOptional.get());
        }
        return result;
    }

    @Operation(summary = "Update client info by ID")
    @PutMapping("/{id}")
    public List<ClientInfo> updateClientInfo(@PathVariable("id") String id, @RequestBody ClientInfo clientInfo) {
        Optional<ClientInfo> existingClientOptional = clientInfoRepository.findById(id);
        List<ClientInfo> result = new ArrayList<>();

        if (existingClientOptional.isPresent()) {
            ClientInfo existingClient = existingClientOptional.get();
            existingClient.setUserName(clientInfo.getUserName());
            existingClient.setPassword(clientInfo.getPassword());
            existingClient.setClientFirst(clientInfo.getClientFirst());
            existingClient.setClientLast(clientInfo.getClientLast());
            existingClient.setFoodBox25(clientInfo.getFoodBox25());
            existingClient.setFoodBox65(clientInfo.getFoodBox65());
            existingClient.setBox25Date(clientInfo.getBox25Date());
            existingClient.setBox65Date(clientInfo.getBox65Date());
            existingClient.setMedications(clientInfo.getMedications());
            existingClient.setClientPicture(clientInfo.getClientPicture());
            existingClient.setAdmin(clientInfo.getAdmin());
            clientInfoRepository.save(existingClient);
            result.add(existingClient);
        }

        return result;
    }
}
