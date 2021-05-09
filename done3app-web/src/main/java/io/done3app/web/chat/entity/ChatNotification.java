package io.done3app.web.chat.entity;

import javax.persistence.*;

@Entity
@Table(name = "chat_notification")
public class ChatNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;
    private String senderId;
    private String senderName;

    public ChatNotification(String id, String senderId, String senderName) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
    }
//
//    public ChatNotification() {
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
