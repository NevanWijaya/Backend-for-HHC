package com.hungerhealthcoalition.backendhhc.repository;

import com.hungerhealthcoalition.backendhhc.model.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientInfoRepository extends JpaRepository<ClientInfo, String> {

}
