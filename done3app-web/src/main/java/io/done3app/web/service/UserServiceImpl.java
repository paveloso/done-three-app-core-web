package io.done3app.web.service;

import io.done3app.web.dao.UserDao;
import io.done3app.web.entity.Role;
import io.done3app.web.entity.User;
import io.done3app.web.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User user = findByEmail(email);
        if (user != null) {
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public void save(AppUser appUser) {
        User user = new User();
        user.setEmail(appUser.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        user.setFirstName(appUser.getFirstName());
        user.setLastName(appUser.getLastName());
        user.setRoles(Arrays.asList(new Role("USER")));

        userDao.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username/email or password.");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
