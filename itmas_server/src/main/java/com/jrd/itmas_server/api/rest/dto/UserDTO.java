package com.jrd.itmas_server.api.rest.dto;

import com.jrd.itmas_server.domain.user.Authority;
import com.jrd.itmas_server.domain.user.User;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jakub on 24.04.16.
 */
public class UserDTO {
    public static final int PASSWORD_MIN_LENGTH = 5;
    public static final int PASSWORD_MAX_LENGTH = 100;

    @Pattern(regexp = "^[a-z0-9]*$")
    @NotNull
    @Size(min = 1, max = 50)
    private String login;

    @NotNull
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private Set<String> authorities;

    private Boolean active;

    public UserDTO() {}

    public UserDTO(User user) {
        this(user.getLogin(), user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getEmail(),
                user.getAuthorities().stream().map(Authority::getName)
                        .collect(Collectors.toSet()), user.isActive());
    }

    public UserDTO(String login, String password, String firstName,
                   String lastName, String email,Set<String> authorities, Boolean active) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.authorities = authorities;
        this.active = active;
    }

    public static int getPasswordMinLength() {
        return PASSWORD_MIN_LENGTH;
    }

    public static int getPasswordMaxLength() {
        return PASSWORD_MAX_LENGTH;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}