package io.done3app.web.dao;

import io.done3app.web.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByEmail(String email) {
        Session currentSession = null;
        try {
            currentSession = sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
//            currentSession = sessionFactory.openSession();
        }

        Query<User> query = currentSession.createQuery("from User where email = :email", User.class);
        query.setParameter("email", email);
        User user = null;

        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }

        return user;
    }

    @Override
    public void save(User user) {
        Session currentSession = null;
        try {
            currentSession = sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
//            currentSession = sessionFactory.openSession();
        }
        currentSession.saveOrUpdate(user);
    }
}
