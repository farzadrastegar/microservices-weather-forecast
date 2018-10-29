package com.myspringproject.microservices.dbservice.model;

import java.util.List;

public class UserLocations {
    String userName;
    List<String> locations;

    public UserLocations() {
    }

    public UserLocations(String userName, List<String> locations) {
        this.userName = userName;
        this.locations = locations;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
}
