package app.service;

import app.entity.User;

public interface IUserServiceJson {
    void save(User user);
    User getUser(String name);
}
