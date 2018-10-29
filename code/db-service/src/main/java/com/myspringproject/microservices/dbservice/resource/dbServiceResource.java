package com.myspringproject.microservices.dbservice.resource;

import com.myspringproject.microservices.dbservice.model.Location;
import com.myspringproject.microservices.dbservice.model.UserLocations;
import com.myspringproject.microservices.dbservice.repository.LocationsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class dbServiceResource {


    private LocationsRepository locationsRepository;

    public dbServiceResource(LocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }

    @CrossOrigin
    @GetMapping("/{username}")
    public List<String> getLocations(@PathVariable("username") final String username) {

        return getlocationsByUserName(username);

    }

    @CrossOrigin
    @PostMapping("/add")
    public List<String> add(@RequestBody final UserLocations userLocations){
        userLocations.getLocations()
                .stream()
                .map (locationStr -> new Location(userLocations.getUserName(), locationStr))
                .forEach(tableRow -> locationsRepository.save(tableRow));

        return getlocationsByUserName(userLocations.getUserName());
    }

    @PostMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") final String username) {
        List<Location> userLocations = locationsRepository.findByUserName(username);

        locationsRepository.deleteInBatch(userLocations);
        return getlocationsByUserName(username);
    }

    public List<String> getlocationsByUserName(@PathVariable("username") String username) {
        return locationsRepository.findByUserName(username)
                .stream()
                .map(Location::getLocation)
                .collect(Collectors.toList());
    }


}
