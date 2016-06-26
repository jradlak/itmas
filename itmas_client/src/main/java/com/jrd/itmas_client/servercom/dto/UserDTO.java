package com.jrd.itmas_client.servercom.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jakub on 24.04.16.
 */
public class UserDTO {

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Set<String> authorities;

    public UserDTO() {}

    public UserDTO(String login, String password, String firstName,
                   String lastName, String email, Set<String> authorities) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.authorities = authorities;
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

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void addAuthority(String authority) {
        if (this.authorities == null) {
            this.authorities = new HashSet<>();
        }

        this.authorities.add(authority);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}