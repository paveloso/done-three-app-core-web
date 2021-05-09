package io.done3app.web.chat.dao;

import io.done3app.web.chat.entity.ChatMessage;
import io.done3app.web.chat.entity.ChatRoom;
import io.done3app.web.chat.entity.MessageStatus;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChatMessageDaoImpl implements ChatMessageDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status) {
        Session currentSession = null;
        try {
            currentSession = sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            currentSession = sessionFactory.openSession();
        }

        Query<ChatMessage> query = currentSession.createQuery("from ChatMessage where senderId = :senderId " +
                "and recipientId = :recipientId and status = :status", ChatMessage.class);
        query.setParameter("senderId", senderId);
        query.setParameter("recipientId", recipientId);
        query.setParameter("status", status);
        List<ChatMessage> messages = null;

        try {
            messages = query.getResultList();
        } catch (Exception e) {
            messages = null;
        }
        return messages != null ? messages.size() : 0;
    }

    @Override
    public List<ChatMessage> findByChatId(String chatId) {
        Session currentSession = null;
        try {
            currentSession = sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            currentSession = sessionFactory.openSession();
        }

        Query<ChatMessage> query = currentSession.createQuery("from ChatMessage where chatId = :chatId", ChatMessage.class);
        query.setParameter("chatId", chatId);
        List<ChatMessage> messages = null;

        try {
            messages = query.getResultList();
        } catch (Exception e) {
            messages = null;
        }
        return messages;
    }

    @Override
    public void save(ChatMessage message) {
        Session currentSession = null;
        try {
            currentSession = sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            currentSession = sessionFactory.openSession();
        }
        currentSession.saveOrUpdate(message);
    }

    @Override
    public ChatMessage findById(String id) {
        Session currentSession = null;
        try {
            currentSession = sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            currentSession = sessionFactory.openSession();
        }

        Query<ChatMessage> query = currentSession.createQuery("from ChatMessage where id = :messageId", ChatMessage.class);
        query.setParameter("messageId", id);
        ChatMessage message = null;

        try {
            message = query.getSingleResult();
        } catch (Exception e) {
            message = null;
        }
        return message;
    }

    @Override
    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
        Session currentSession = null;
        try {
            currentSession = sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            currentSession = sessionFactory.openSession();
        }

        Query query = currentSession.createQuery("update ChatMessage set status = :status " +
                "where senderId = :senderId and recipientId = :recipientId");
        query.setParameter("status", status);
        query.setParameter("senderId", senderId);
        query.setParameter("recipientId", recipientId);

        query.executeUpdate();
    }
}
