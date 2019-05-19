package com.netcracker.service;


import com.netcracker.dao.ItembodytypeDAO;
import com.netcracker.dao.ItembodytypeDAOImpl;
import com.netcracker.model.Itembodytype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItembodyServiceImpl implements ItembodyService {

    private ItembodytypeDAO itembodytypeDAO;

    @Autowired
    public void setItembodytypeDAO(ItembodytypeDAOImpl itembodytypeDAO) {
        this.itembodytypeDAO = itembodytypeDAO;
    }

    @Override
    public void addItem(Itembodytype itembodytype) {
        itembodytypeDAO.addItem(itembodytype);
    }

    @Override
    public List<Itembodytype> findAllItems() {
        return itembodytypeDAO.findAllItems();
    }

    @Override
    public List<Itembodytype> findAllItemsByName() {
        return itembodytypeDAO.findAllItemsByName();
    }

    @Override
    public Itembodytype findById(Long id) {
        return itembodytypeDAO.findById(id);
    }

    @Override
    public void editItembodytype(Itembodytype itembodytype) {
        Itembodytype entity = itembodytypeDAO.findById(itembodytype.getId());
        if (entity != null) {
            entity.setName(itembodytype.getName());
        }
        itembodytypeDAO.editItembodytype(entity);
    }

    @Override
    public void removeItembodytypeById(Integer id) {
        itembodytypeDAO.removeItembodytypeById(id);
    }

    @Override
    public void removeItembody(Itembodytype itembodytype) {
        itembodytypeDAO.removeItembody(itembodytype);
    }

    @Override
    public boolean isNameUnique(String name) {
        return itembodytypeDAO.findByName(name) == null;
    }


}
