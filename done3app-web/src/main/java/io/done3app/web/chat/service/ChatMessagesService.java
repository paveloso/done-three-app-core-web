package io.done3app.web.chat.service;

import io.done3app.web.chat.entity.ChatMessage;
import io.done3app.web.chat.entity.MessageStatus;

import java.util.List;

public interface ChatMessagesService {

    ChatMessage save(ChatMessage chatMessage);

    long countNewMessages(String senderId, String recipientId);

    List<ChatMessage> findChatMessages(String senderId, String recipientId);

    ChatMessage findById(String id);

    void updateStatuses(String senderId, String recipientId, MessageStatus status);
}
