package io.done3app.web.chat.dao;

import io.done3app.web.chat.entity.ChatMessage;
import io.done3app.web.chat.entity.MessageStatus;

import java.util.List;

public interface ChatMessageDao {

    long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);

    void save(ChatMessage message);

    ChatMessage findById(String id);

    void updateStatuses(String senderId, String recipientId, MessageStatus status);
}
