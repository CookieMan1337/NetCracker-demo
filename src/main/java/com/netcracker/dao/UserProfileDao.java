package com.netcracker.dao;

import com.netcracker.model.UserProfile;

import java.util.List;


public interface UserProfileDao {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(long id);
}
