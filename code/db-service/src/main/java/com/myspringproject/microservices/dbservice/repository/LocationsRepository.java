package com.myspringproject.microservices.dbservice.repository;

import com.myspringproject.microservices.dbservice.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationsRepository extends JpaRepository<Location, Integer> {
    List<Location> findByUserName(String username);
}
