package io.done3app.web.chat.service;

import java.util.Optional;

public interface ChatRoomService {

    Optional<String> getChatId(String senderId, String recipientId, boolean createIfNotExist);
}
