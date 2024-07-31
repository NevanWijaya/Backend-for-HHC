package com.hungerhealthcoalition.backendhhc.controller;


import com.hungerhealthcoalition.backendhhc.model.Goals;
import com.hungerhealthcoalition.backendhhc.repository.GoalsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Goal Table", description = "Endpoints for managing Client's Goals information")
@RestController
@RequestMapping("/api/Goals")
public class GoalController {
    private GoalsRepository goalsRepository;

    public GoalController(GoalsRepository goalsRepository) {
        this.goalsRepository = goalsRepository;
    }

    @Operation(summary = "Retrieves all Goals")
    @GetMapping
    public List<Goals> getAllGoals() {
        return goalsRepository.findAll();
    }


    @Operation(summary = "Retrieves goals by clientId")
    @GetMapping("/{id}")
    public List<Goals> getClientGoalsbyID(@PathVariable("id") int id) {
        return goalsRepository.findGoalsByClientInfoClientID(id);
    }

    @Operation(summary = "Retrieves Goal by goal name and Client ID")
    @GetMapping("/{id}/{goalName}")
    public Optional<Goals> getClientGoalByIDandName(@PathVariable("id") int id, @PathVariable("goalName") String goalName) {
        return goalsRepository.findGoalsByClientInfoClientIDAndGoalName(id, goalName);
    }


    @Operation(summary = "Adds new goal")
    @PostMapping
    public Goals addGoal(@RequestBody Goals goals) {
        Optional<Goals> exisitingGoal = goalsRepository.findGoalsByClientInfoClientIDAndGoalName(
                goals.getClientInfo().getClientID(), goals.getGoalName()
        );
        if (exisitingGoal.isPresent()) {
            throw new RuntimeException("Goal with that client Id" + goals.getClientInfo().getClientID() + " and goalName" + goals.getGoalName() + "already exists");
        }
        goalsRepository.save(goals);
        return goals;
    }


    @Operation(summary = "Updates goal by Client ID and Goal Name")
    @PutMapping("/{id}/{goalName}")
    public List<Goals> updateGoal(@PathVariable("id") int id, @RequestBody Goals goals, @PathVariable("goalName") String goalName) {
        if (!goalsRepository.existsGoalsByClientInfoClientID(id)) {
            throw new RuntimeException("Client with ID " + id + " not found");
        }
        Optional<Goals> exisitingGoalOptional = goalsRepository.findGoalsByClientInfoClientIDAndGoalName(id, goalName);
        List<Goals> result = new ArrayList<>();
        if (exisitingGoalOptional.isPresent()) {
            Goals existingGoal = exisitingGoalOptional.get();
            existingGoal.setGoalName(goals.getGoalName());
            existingGoal.setGoalValue(goals.getGoalValue());
            existingGoal.setStartValue(goals.getStartValue());
            existingGoal.setCurrentValue(goals.getCurrentValue());
            existingGoal.setGoalDesc(goals.getGoalDesc());
            goalsRepository.save(existingGoal);
            result.add(existingGoal);
        }
        return result;

    }

    @Operation(summary = "Deletes goal by Client ID and Goal Name")
    @DeleteMapping("/{id}/{goalName}")
    @Transactional
    public ResponseEntity<Goals> deleteGoalbyClientIDAndGoalID(@PathVariable("id") int clientId, @PathVariable("goalName") String goalName) {
        goalsRepository.deleteGoalsByClientInfoClientIDAndGoalName(clientId, goalName);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletes all goals by Client ID")
    @DeleteMapping("/{id}/")
    @Transactional
    public ResponseEntity<Goals> deleteAllGoalByClientID(@PathVariable("id") int clientId) {
        goalsRepository.deleteGoalsByClientInfoClientID(clientId);
        return ResponseEntity.noContent().build();
    }

}
