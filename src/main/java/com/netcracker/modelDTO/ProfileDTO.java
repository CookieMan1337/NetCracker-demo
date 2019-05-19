package com.netcracker.modelDTO;

import com.netcracker.model.User;
import org.hibernate.validator.constraints.Email;

public class ProfileDTO {
    private long id;
    private String login;
    private String oldPassword;
    private String password;
    private String passwordConfirm;
    private String firstname;
    private String lastname;
    @Email
    private String email;
    private String action = "";

    public ProfileDTO() {
    }

    public ProfileDTO(String login, String oldPassword, String password, String passwordConfirm, String firstname, String lastname, String email) {
        this.login = login;
        this.oldPassword = oldPassword;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public ProfileDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.oldPassword = "";
        this.password = "";
        this.passwordConfirm = "";
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
    }

    public void copyProperties(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.oldPassword = "";
        this.password = "";
        this.passwordConfirm = "";
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileDTO)) return false;

        ProfileDTO that = (ProfileDTO) o;

        if (!password.equals(that.password)) return false;
        if (!oldPassword.equals(that.oldPassword)) return false;
        if (!passwordConfirm.equals(that.passwordConfirm)) return false;
        if (!firstname.equals(that.firstname)) return false;
        if (!lastname.equals(that.lastname)) return false;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        int result = password.hashCode();
        result = 31 * result + oldPassword.hashCode();
        result = 31 * result + passwordConfirm.hashCode();
        result = 31 * result + firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLogin() {
        return login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
