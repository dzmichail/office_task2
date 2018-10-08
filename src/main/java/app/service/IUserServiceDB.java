package app.service;

import app.entity.User;

import java.sql.Connection;
import java.util.Collection;

public interface IUserServiceDB {
    void save(User user);
    User getUser(String name);
    Connection getConn();
    Collection<User> getUsers();
    Collection<User> getUserOrganization(String organizationName);
}
