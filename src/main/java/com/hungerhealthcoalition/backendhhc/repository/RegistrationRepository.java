package com.hungerhealthcoalition.backendhhc.repository;

import com.hungerhealthcoalition.backendhhc.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, String> {
    public List<Registration> findRegistrationByClientInfoClientID(int id);

    public List<Registration> findRegistrationByEventsEventId(int eventID);

    public Optional<Registration> findRegistrationByClientInfoClientIDAndEventsEventId(int id, int eventID);

    public void deleteRegistrationByClientInfoClientIDAndEventsEventId(int id, int eventID);

    public void deleteRegistrationByEventsEventId(int eventID);

    public boolean existsByClientInfoClientID(int id);

}
