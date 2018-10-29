package com.myspringproject.microservices.reportservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/report")
public class reportServiceResource {

    @Autowired
    RestTemplate restTemplate;

    @CrossOrigin
    @GetMapping("{username}")
    public List<String> getForecast(@PathVariable("username") final String username) {
        ResponseEntity<List<String>> userLocations = restTemplate.exchange("http://db-service/rest/db/" + username,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});

        return userLocations.getBody()
                .stream()
                .map(locationStr ->
                        restTemplate.exchange(
                                "http://weather-service/rest/weather/" + locationStr,
                                HttpMethod.GET,
                                null,
                                new ParameterizedTypeReference<String>() {}))
                .map(forecastEntity -> forecastEntity.getBody())
                .collect(Collectors.toList());
    }
}
