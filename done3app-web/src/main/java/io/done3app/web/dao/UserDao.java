package io.done3app.web.dao;

import io.done3app.web.entity.User;

public interface UserDao {

    User findByEmail(String email);

    void save(User user);

}
