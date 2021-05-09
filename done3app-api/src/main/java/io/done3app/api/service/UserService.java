package io.done3app.api.service;

import io.done3app.api.entity.User;
import io.done3app.api.repository.RoleRepository;
import io.done3app.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByLogin(String login) {
        return userRepo.findByEmail(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User user = userRepo.findByEmail(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
