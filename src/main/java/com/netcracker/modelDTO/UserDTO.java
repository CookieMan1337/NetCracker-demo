package com.netcracker.modelDTO;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    private long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String description;
    private Set<Long> logShops = new HashSet<>();
    private Set<Long> userProfiles = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Long> getLogShops() {
        return logShops;
    }

    public void setLogShops(Set<Long> logShops) {
        this.logShops = logShops;
    }

    public Set<Long> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<Long> userProfiles) {
        this.userProfiles = userProfiles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
