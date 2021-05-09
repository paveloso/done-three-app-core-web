package io.done3app.web.chat.dao;

import io.done3app.web.chat.entity.ChatRoom;

import java.util.Optional;

public interface ChatRoomDao {

    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);

    void save(ChatRoom chatRoom);

}
