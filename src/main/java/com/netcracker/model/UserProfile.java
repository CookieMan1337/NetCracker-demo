package com.netcracker.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_profile")
public class UserProfile implements Serializable {

    @Id
    @SequenceGenerator(name = "users_profile_seq", sequenceName = "users_profile_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "users_profile_seq")
    private long id;

    @Column(name = "type", length = 15, unique = true, nullable = false)
    private String type = UserProfileType.USER.getUserProfileType();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}, mappedBy = "userProfiles")
    private Set<User> users = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProfile)) return false;

        UserProfile that = (UserProfile) o;

        if (id != that.id) return false;
        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserProfile [id=" + id + ", type=" + type + "]";
    }


    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
