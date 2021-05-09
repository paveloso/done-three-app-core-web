package io.done3app.web.chat.controller;

import io.done3app.web.chat.entity.ChatMessage;
import io.done3app.web.chat.entity.ChatNotification;
import io.done3app.web.chat.service.ChatMessagesService;
import io.done3app.web.chat.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatMessagesService messagesService;
    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage message) {
        var chatId = chatRoomService.getChatId(message.getSenderId(), message.getRecipientId(), true);
        message.setChatId(chatId.get());

        ChatMessage saved = messagesService.save(message);
        messagingTemplate.convertAndSendToUser(
                message.getRecipientId(),
                "/queue/messages",
                new ChatNotification(saved.getId(), saved.getSenderId(), saved.getSenderName()));
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(@PathVariable String senderId, @PathVariable String recipientId) {
        return ResponseEntity.ok(messagesService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages(@PathVariable String senderId, @PathVariable String recipientId) {
        return ResponseEntity.ok(messagesService.findChatMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage(@PathVariable String id) {
        return ResponseEntity.ok(messagesService.findById(id));
    }
}
