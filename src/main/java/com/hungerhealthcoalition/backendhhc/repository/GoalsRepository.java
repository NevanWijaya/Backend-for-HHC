package com.hungerhealthcoalition.backendhhc.repository;


import com.hungerhealthcoalition.backendhhc.model.Goals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoalsRepository extends JpaRepository<Goals, String> {
    public List<Goals> findGoalsByClientInfoClientID(int id);

    public Optional<Goals> findGoalsByClientInfoClientIDAndGoalName(int id, String name);


    //delete all goals from a certain client
    public void deleteGoalsByClientInfoClientID(int id);

    //delete specific goal
    public void deleteGoalsByClientInfoClientIDAndGoalName(int id, String name);


    public boolean existsGoalsByClientInfoClientID(int id);


}
