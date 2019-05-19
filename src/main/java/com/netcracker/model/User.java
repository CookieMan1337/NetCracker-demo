package com.netcracker.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @SequenceGenerator(name = "users_seq", sequenceName = "users_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "users_seq")
    @Column(name = "id")
    private long id;

    @Column(name = "login", nullable = false, unique = true)
    @NotEmpty
    private String login;

    @Column(name = "password", nullable = false)
    @NotEmpty
    private String password;

    @Column(name = "firstname", nullable = false)
    @NotEmpty
    private String firstname;

    @Column(name = "lastname", nullable = false)
    @NotEmpty
    private String lastname;

    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty
    @Email
    private String email;

    public User() {
        this.login = "";
    }
    /*    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<LogShop> logShops = new HashSet<>();*/

    @ManyToMany(fetch = FetchType.LAZY/*, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}*/)
    @JoinTable(name = "app_user_user_profile",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_profile_id")})
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();


    @OneToMany(mappedBy = "user")
    // @JoinColumn(name = "user_id")
    private Set<Order> orders = new HashSet<>();

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
/*
    public Set<LogShop> getLogShops() {
        return logShops;
    }

    public void setLogShops(Set<LogShop> logShops) {
        this.logShops = logShops;
    }*/

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    public void addUserProfile(UserProfile userProfile) {
        userProfiles.add(userProfile);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        if (!firstname.equals(user.firstname)) return false;
        if (!lastname.equals(user.lastname)) return false;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

}
