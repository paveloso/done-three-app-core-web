package io.done3app.web.chat.service;

import io.done3app.web.chat.dao.ChatMessageDao;
import io.done3app.web.chat.entity.ChatMessage;
import io.done3app.web.chat.entity.MessageStatus;
import io.done3app.web.chat.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatMessagesServiceImpl implements ChatMessagesService {

    @Autowired
    private ChatMessageDao chatMessageDao;
    @Autowired
    private ChatRoomService chatRoomService;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        chatMessageDao.save(chatMessage);
        return chatMessage;
    }

    @Override
    public long countNewMessages(String senderId, String recipientId) {
        return chatMessageDao.countBySenderIdAndRecipientIdAndStatus(senderId, recipientId, MessageStatus.RECEIVED);
    }

    @Override
    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);

        var messages = chatId.map(cId -> chatMessageDao.findByChatId(cId)).orElse(new ArrayList<>());

        if (messages.size() > 0) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }

        return messages;
    }

    @Override
    public ChatMessage findById(String id) {
        Optional<ChatMessage> chatMessage = Optional.of(chatMessageDao.findById(id));
        return chatMessage.map(cm -> {
            cm.setStatus(MessageStatus.DELIVERED);
            chatMessageDao.save(cm);
            return cm;
        }).orElseThrow(() -> new ResourceNotFoundException("can't find message (" + id + ")"));
    }

    @Override
    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
        chatMessageDao.updateStatuses(senderId, recipientId, status);
    }
}
