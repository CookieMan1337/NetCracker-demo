package com.netcracker.service;

import com.netcracker.dao.UserDao;
import com.netcracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao dao;

    @Autowired
    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(long id) {
        return dao.findById(id);
    }

    @Override
    public User findByLogin(String login) {
        User user = dao.findByLogin(login);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = dao.findByEmail(email);
        return user;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
    }

    @Override
    public void updateCart(User user) {
        User entity = dao.findById(user.getId());
        entity.setOrders(user.getOrders());
        dao.edit(user);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */

    @Override
    public void updateUser(User user) {
        User entity = dao.findById(user.getId());
        if (entity != null) {
            entity.setLogin(user.getLogin());
            if (!passwordEncoder.matches(user.getPassword(), entity.getPassword())) {
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            entity.setFirstname(user.getFirstname());
            entity.setLastname(user.getLastname());
            entity.setEmail(user.getEmail());
            entity.setUserProfiles(user.getUserProfiles());
//			entity.setCart(user.getCart());
            dao.save(user);
        }
    }

    @Override
    public void deleteUserByLogin(String login) {
        dao.deleteByLogin(login);

    }

    @Override
    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    @Override
    public boolean isUserLoginUnique(Long id, String login) {
        User user = findByLogin(login);
        return (user == null || ((id != null) && (user.getId() == id)));
    }

    @Override
    public boolean isUserEmailUnique(Long id, String email) {
        User user = findByEmail(email);
        return (user == null || ((id != null) && (user.getId() == id)));
    }


}
