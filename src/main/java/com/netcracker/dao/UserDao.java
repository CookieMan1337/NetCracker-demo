package com.netcracker.dao;

import com.netcracker.model.User;

import java.util.List;


public interface UserDao {

    User findById(long id);

    User findByLogin(String login);

    User findByEmail(String email);

    void save(User user);

    void persist(User user);

    void edit(User user);

    void deleteByLogin(String login);

    List<User> findAllUsers();

}

