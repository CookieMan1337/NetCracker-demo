package com.netcracker.dao;

import com.netcracker.model.Storage;

import java.util.List;

public interface StorageDAO {
    void addStorage(Storage storage);

    List<Storage> findAllStorages();

    Storage findById(Long id);

    void editStorage(Storage storage);

    void removeStorageById(Long id);

    void removeStorage(Storage storage);
}
