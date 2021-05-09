package io.done3app.web.service;

import io.done3app.web.entity.User;
import io.done3app.web.user.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    void save(AppUser appUser);

}
