package io.done3app.web.chat.service;

import io.done3app.web.chat.dao.ChatRoomDao;
import io.done3app.web.chat.entity.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatRoomDao chatRoomDao;

    @Override
    public Optional<String> getChatId(String senderId, String recipientId, boolean createIfNotExist) {

        return chatRoomDao.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if (!createIfNotExist) {
                        return Optional.empty();
                    }
                    var chatId = String.format("%s_%s", senderId, recipientId);

                    ChatRoom senderRecipient = new ChatRoom(chatId, senderId, recipientId);

                    ChatRoom recipientSender = new ChatRoom(chatId, recipientId, senderId);

                    chatRoomDao.save(senderRecipient);
                    chatRoomDao.save(recipientSender);

                    return Optional.of(chatId);
                });
    }
}
