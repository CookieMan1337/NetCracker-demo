package com.netcracker.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "itembodytypes")
public class Itembodytype implements Serializable {
    @Id
    @SequenceGenerator(name = "itembodytypes_seq", sequenceName = "shop.itembodytypes_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "itembodytypes_seq")
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @OneToMany(mappedBy = "itembodytype", cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<>();


    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Itembodytype)) return false;

        Itembodytype that = (Itembodytype) o;

        if (id != that.id) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Itembodytype{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
