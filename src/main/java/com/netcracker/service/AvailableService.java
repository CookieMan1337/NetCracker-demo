package com.netcracker.service;

import com.netcracker.model.Available;
import com.netcracker.model.AvailableId;

import java.util.List;

public interface AvailableService {
    void addAvailable(Available available);

    List<Available> findAllAvailables();

    Available findById(AvailableId id);

    Available findByNumId(long item, long storage);

    void editAvailable(Available available);

    void removeAvailableById(AvailableId id);

    void removeAvailable(Available available);
}
