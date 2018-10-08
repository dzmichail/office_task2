package app.service;

import app.entity.User;

import java.util.Collection;

public interface IUserServiceHib {
    void save(User user);
    User getUser(String name);
    Collection<User> getAllUsers();
}
