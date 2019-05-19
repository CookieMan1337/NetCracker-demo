package com.netcracker.dao;

import com.netcracker.model.Available;
import com.netcracker.model.AvailableId;

import java.util.List;

public interface AvailableDAO {

    void addItem(Available available);

    List<Available> findAllAvailables();

    Available findById(AvailableId id);

    void editAvailable(Available available);

    void removeAvailableById(AvailableId id);

    void removeAvailable(Available available);
}
