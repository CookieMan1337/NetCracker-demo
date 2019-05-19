package com.netcracker.service;

import com.netcracker.model.User;

import java.util.List;


public interface UserService {

    User findById(long id);

    User findByLogin(String login);

    User findByEmail(String email);

    void saveUser(User user);

    void updateCart(User user);

    void updateUser(User user);

    void deleteUserByLogin(String login);

    List<User> findAllUsers();

    boolean isUserLoginUnique(Long id, String login);

    boolean isUserEmailUnique(Long id, String email);

}