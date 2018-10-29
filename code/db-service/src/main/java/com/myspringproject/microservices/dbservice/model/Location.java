package com.myspringproject.microservices.dbservice.model;

import javax.persistence.*;

@Entity
@Table(name = "location", catalog = "test2018")
public class Location {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "loc")
    private String location;

    public Location() {
    }

    public Location(String userName, String location) {
        this.userName = userName;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
