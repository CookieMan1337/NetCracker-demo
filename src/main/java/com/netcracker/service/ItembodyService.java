package com.netcracker.service;

import com.netcracker.model.Itembodytype;

import java.util.List;

public interface ItembodyService {
    void addItem(Itembodytype itembodytype);

    List<Itembodytype> findAllItems();

    List<Itembodytype> findAllItemsByName();

    Itembodytype findById(Long id);

    void editItembodytype(Itembodytype itembodytype);

    void removeItembodytypeById(Integer id);

    void removeItembody(Itembodytype itembodytype);

    boolean isNameUnique(String name);
}
