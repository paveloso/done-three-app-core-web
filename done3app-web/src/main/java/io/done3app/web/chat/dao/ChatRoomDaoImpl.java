package io.done3app.web.chat.dao;

import io.done3app.web.chat.entity.ChatRoom;
import io.done3app.web.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ChatRoomDaoImpl implements ChatRoomDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId) {

        Session currentSession = null;
        try {
            currentSession = sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            currentSession = sessionFactory.openSession();
        }

        Query<ChatRoom> query = currentSession.createQuery("from ChatRoom where senderId = :senderId and recipientId = :recipientId", ChatRoom.class);
        query.setParameter("senderId", senderId);
        query.setParameter("recipientId", recipientId);
        Optional<ChatRoom> chatRoom = Optional.empty();

        try {
            chatRoom = Optional.of(query.getSingleResult());
        } catch (Exception e) {
            chatRoom = Optional.empty();
        }

        return chatRoom;
    }

    @Override
    public void save(ChatRoom chatRoom) {
        Session currentSession = null;
        try {
            currentSession = sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            currentSession = sessionFactory.openSession();
        }
        currentSession.saveOrUpdate(chatRoom);
    }
}
