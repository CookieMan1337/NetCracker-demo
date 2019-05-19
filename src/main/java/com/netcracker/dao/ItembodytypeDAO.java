package com.netcracker.dao;


import com.netcracker.model.Itembodytype;

import java.util.List;

public interface ItembodytypeDAO {

    void addItem(Itembodytype itembodytype);

    List<Itembodytype> findAllItems();

    List<Itembodytype> findAllItemsByName();

    Itembodytype findById(Long id);

    Itembodytype findByName(String name);

    void editItembodytype(Itembodytype itembodytype);

    void removeItembodytypeById(Integer id);

    void removeItembody(Itembodytype itembodytype);
}
